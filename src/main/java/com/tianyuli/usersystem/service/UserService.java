package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.pojo.User;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.req.LoginRequest;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService<User, String> {

    Object beforeRegister(RegisterRequest registerRequest);

    boolean checkCaptcha(RegisterRequest registerRequest) throws NullPointerException;

    RespResult registerUser(RegisterRequest registerRequest);

    User getUserByUsername(String username);

    boolean checkVerified(User user);

    boolean checkPassword(User user, LoginRequest loginRequest);
}
