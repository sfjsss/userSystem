package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserDao;
import com.tianyuli.usersystem.pojo.*;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.common.utils.MD5Utils;
import com.tianyuli.usersystem.rpcDomain.req.LoginRequest;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private RegisterRecordService registerRecordService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserTagService userTagService;

    @Override
    public Object beforeRegister(RegisterRequest registerRequest) {

        return null;
    }

    @Override
    public boolean checkCaptcha(RegisterRequest registerRequest) throws NullPointerException {
        String captcha = registerRecordService.getCaptchaByUsername(registerRequest.getUsername());
        return StringUtils.equals(captcha, registerRequest.getCaptcha());
    }

    @Override
    public RespResult registerUser(RegisterRequest registerRequest) {
        User user = userDao.getByUsername(registerRequest.getUsername());
        user.setVerified(true);
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

        UserTag userTag = new UserTag();
        userTag.setUserId(userId);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);

        userPreferenceService.save(userPreference);
        addressService.save(address);
        userProfileService.save(userProfile);
        userTagService.save(userTag);
    }

    @Override
    protected JpaRepository getJpaRepository() {
        return this.userDao;
    }

    @Override
    public boolean checkPassword(User user, LoginRequest loginRequest) {
        return StringUtils.equals(user.getPassword(), MD5Utils.getMD5(loginRequest.getPassword()));
    }
}
