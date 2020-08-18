package com.tianyuli.usersystem.rpcDomain.common.strategy;

import com.tianyuli.usersystem.dao.RegisterRecordDao;
import com.tianyuli.usersystem.dao.UserDao;
import com.tianyuli.usersystem.pojo.RegisterRecord;
import com.tianyuli.usersystem.pojo.User;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.common.utils.MD5Utils;
import com.tianyuli.usersystem.rpcDomain.common.utils.UUIDUtils;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterProcessingStrategyImpl implements UserStrategy {

    @Autowired
    private RegisterRecordDao registerRecordDao;

    @Autowired
    private UserDao userDao;

    @Override
    public RespResult doProcessor(RegisterRequest registerRequest, OperatorStrategyEnum operatorStrategyEnum) {
        if (operatorStrategyEnum == OperatorStrategyEnum.SUCCESS) {
            RegisterRecord registerRecord = new RegisterRecord();
            registerRecord.setId(UUIDUtils.get());
            registerRecord.setEmail(registerRequest.getEmail());
            registerRecord.setCaptcha(registerRequest.getCaptcha());
            registerRecord.setSendTime(new Date(System.currentTimeMillis()));
            registerRecordDao.save(registerRecord);

            // save user
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(MD5Utils.getMD5(registerRequest.getPassword()));
            user.setId(UUIDUtils.get());
            userDao.save(user);
            return new RespResult(ResultCode.REGISTER_CAPTCHA_SEND);
        } else {
            return new RespResult(ResultCode.MAIL_SEND_FAIL);
        }
    }
}
