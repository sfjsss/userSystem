package com.tianyuli.usersystem.controller;

import com.tianyuli.usersystem.pojo.User;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.common.utils.JwtTokenUtil;
import com.tianyuli.usersystem.rpcDomain.req.LoginRequest;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import com.tianyuli.usersystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @PostMapping(value = "login", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult login(HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        User user = userService.getUserByUsername(loginRequest.getUsername());
        if (!userService.checkVerified(user)) {
            return new RespResult(ResultCode.USER_UN_VERIFIED);
        }
        if (!userService.checkPassword(user, loginRequest)) {
            return new RespResult(ResultCode.WRONG_PASSWORD);
        }

        String token = JwtTokenUtil.createJWT(user.getId(), user.getUsername());
        logger.info("user " + user.getUsername() + " login successfully");
        logger.info("Token: " + token);
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        Map<String, String> map = new HashMap<>();
        map.put("token", JwtTokenUtil.TOKEN_PREFIX + token);
        return new RespResult(ResultCode.SUCCESS, map);
    }
}
