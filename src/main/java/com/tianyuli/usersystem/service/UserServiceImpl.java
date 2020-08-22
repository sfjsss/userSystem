package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserDao;
import com.tianyuli.usersystem.pojo.*;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.common.component.validate.ReqValidateManager;
import com.tianyuli.usersystem.rpcDomain.common.exception.ValidateException;
import com.tianyuli.usersystem.rpcDomain.common.strategy.ContextMapper;
import com.tianyuli.usersystem.rpcDomain.common.strategy.OperatorStrategyEnum;
import com.tianyuli.usersystem.rpcDomain.common.utils.MD5Utils;
import com.tianyuli.usersystem.rpcDomain.req.LoginRequest;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import com.tianyuli.usersystem.rpcDomain.resp.ArticleResp;
import com.tianyuli.usersystem.rpcDomain.resp.UserCenterVOResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private RegisterRecordService registerRecordService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ReqValidateManager reqValidateManager;

    @Autowired
    private ToolService toolService;

    @Autowired
    private ContextMapper contextMapper;

    @Override
    public RespResult beforeRegister(RegisterRequest registerRequest) {
        try {
            reqValidateManager.doExecute(registerRequest);
        } catch (ValidateException e) {
            return new RespResult(e.getResultCode());
        }
        boolean isSend = toolService.sendRegisterMail(registerRequest);
        OperatorStrategyEnum operatorStrategyEnum = isSend ? OperatorStrategyEnum.SUCCESS : OperatorStrategyEnum.FAIL;
        return contextMapper.loadProcessor(operatorStrategyEnum).doProcessor(registerRequest, operatorStrategyEnum);
    }

    @Override
    public boolean checkCaptcha(RegisterRequest registerRequest) throws NullPointerException {
        String captcha = registerRecordService.getCaptchaByUsername(registerRequest.getUsername());
        return StringUtils.equals(captcha, registerRequest.getCaptcha());
    }

    @Override
    public RespResult registerUser(RegisterRequest registerRequest) {
        User user = userDao.getByUsername(registerRequest.getUsername());
        user.setIsVerified(true);
        userDao.save(user);
        initUserInfo(user);
        return new RespResult(ResultCode.REGISTERED_SUCCESS);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public boolean checkVerified(User user) {
        return user != null && user.getVerified();
    }

    private void initUserInfo(User user) {
        String userId = user.getId();
        Address address = new Address();
        address.setUserId(userId);

        UserPreference userPreference = new UserPreference();
        userPreference.setUserId(userId);
        userPreference.setOtherUserMessageNotice("1");
        userPreference.setSysMessageNotice("1");
        userPreference.setTodoNotice("1");

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);

        userPreferenceService.save(userPreference);
        addressService.save(address);
        userProfileService.save(userProfile);
    }

    @Override
    protected JpaRepository getJpaRepository() {
        return this.userDao;
    }

    @Override
    public boolean checkPassword(User user, LoginRequest loginRequest) {
        return StringUtils.equals(user.getPassword(), MD5Utils.getMD5(loginRequest.getPassword()));
    }

    @Override
    public RespResult getAccountCenterInfo(String userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        if (!optionalUser.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        UserCenterVOResp userCenterVOResp = new UserCenterVOResp();
        User user = optionalUser.get();
        userCenterVOResp.setUsername(user.getUsername());
        Optional<Address> optionalAddress = addressService.findById(userId);
        Address address = optionalAddress.get();
        String provinceAndCity = address.getProvince() == null ? "" : address.getProvince() + (address.getCity() == null ? "" : address.getCity());
        userCenterVOResp.setProvinceAndCity(provinceAndCity);
        userCenterVOResp.setPersonalProfile(userProfileService.findById(userId).get().getPersonalProfile());
        userCenterVOResp.setUserTagList(userTagService.getUserTagList(userId));
        List<Article> articleList = articleService.getRecentArticles();
        List<ArticleResp> articleRespList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleResp articleResp = new ArticleResp();
            BeanUtils.copyProperties(article, articleResp);
            articleRespList.add(articleResp);
        }
        userCenterVOResp.setArticleList(articleRespList);
        return new RespResult(ResultCode.SUCCESS, userCenterVOResp);
    }
}
