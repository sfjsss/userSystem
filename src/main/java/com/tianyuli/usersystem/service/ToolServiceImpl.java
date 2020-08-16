package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.rpcDomain.common.utils.RandomCaptcha;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private MailService mailService;

    @Override
    public String getCaptcha() {
        return null;
    }

    @Override
    public boolean sendRegisterMail(RegisterRequest registerRequest) {
        String captcha = RandomCaptcha.get();
        StringBuilder content = new StringBuilder();
        content.append("Hello ").append(registerRequest.getUsername()).append(", your captcha is ").append(captcha);
        return mailService.sendSimpleMail(registerRequest.getEmail(), "new user registration", content.toString());
    }
}
