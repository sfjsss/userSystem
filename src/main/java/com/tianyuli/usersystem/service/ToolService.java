package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface ToolService {

    String getCaptcha();

    boolean sendRegisterMail(RegisterRequest registerRequest);
}
