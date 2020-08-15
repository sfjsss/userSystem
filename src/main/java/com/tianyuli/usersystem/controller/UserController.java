package com.tianyuli.usersystem.controller;

import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import com.tianyuli.usersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "get/Captcha", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult getCaptcha(@RequestBody RegisterRequest registerRequest) {
//        return userService.beforeRegister(registerRequest);
        return null;
    }


    @PostMapping(value = "register", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult register(@RequestBody RegisterRequest registerRequest) {
        try {
            if (!userService.checkCaptcha(registerRequest)) {
                return new RespResult(ResultCode.WRONG_CAPTCHA);
            }
        } catch (NullPointerException npt) {
            return new RespResult(ResultCode.REGISTER_RECORD_IS_EMPTY);
        }
        return userService.registerUser(registerRequest);
    }
}
