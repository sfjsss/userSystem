package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserPreferenceDao;
import com.tianyuli.usersystem.pojo.User;
import com.tianyuli.usersystem.pojo.UserPreference;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.req.UserPreferenceReq;
import com.tianyuli.usersystem.rpcDomain.resp.UserPreferenceResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPreferenceServiceImpl extends BaseServiceImpl<UserPreference, String> implements UserPreferenceService {

    @Autowired
    private UserPreferenceDao userPreferenceDao;

    @Autowired
    private UserService userService;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.userPreferenceDao;
    }

    @Override
    public RespResult updateNoticeByUserId(String userId, UserPreferenceReq userPreferenceReq) {
        UserPreference userPreference = new UserPreference();
        BeanUtils.copyProperties(userPreferenceReq, userPreference);
        userPreference.setUserId(userId);
        userPreferenceDao.save(userPreference);
        return new RespResult(ResultCode.SUCCESS);
    }

    @Override
    public RespResult getNoticeByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return new RespResult(ResultCode.PARAM_IS_BLANK);
        }
        Optional<User> user = userService.findById(userId);
        if (!user.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        UserPreference userPreference = userPreferenceDao.findByUserId(userId);
        UserPreferenceResp userPreferenceResp = new UserPreferenceResp();
        BeanUtils.copyProperties(userPreference, userPreferenceResp);
        return new RespResult(ResultCode.SUCCESS, userPreferenceResp);
    }
}
