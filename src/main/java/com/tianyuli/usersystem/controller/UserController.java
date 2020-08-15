package com.tianyuli.usersystem.controller;

import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "get/Captcha", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object getCaptcha(@RequestBody RegisterRequest registerRequest) {
        
    }
}
