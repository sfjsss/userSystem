package com.tianyuli.usersystem.controller;

import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.utils.JwtTokenUtil;
import com.tianyuli.usersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/center", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult center(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        return userService.getAccountCenterInfo(JwtTokenUtil.getUserIdByAuthorHead(headerValue));
    }
}
