package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserDao;
import com.tianyuli.usersystem.pojo.User;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private RegisterRecordService registerRecordService;

    @Autowired
    private UserDao userDao;

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

    private void initUserInfo(User user) {

    }
}
