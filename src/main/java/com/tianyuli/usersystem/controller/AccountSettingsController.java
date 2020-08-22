package com.tianyuli.usersystem.controller;

import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.utils.JwtTokenUtil;
import com.tianyuli.usersystem.rpcDomain.req.UserPreferenceReq;
import com.tianyuli.usersystem.rpcDomain.req.UserProfileReq;
import com.tianyuli.usersystem.service.UserPreferenceService;
import com.tianyuli.usersystem.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account/settings")
@CrossOrigin
public class AccountSettingsController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping(value = "/notice/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showNotice(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.getNoticeByUserId(userId);
    }

    @PostMapping(value = "/notice/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult updateNotice(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue, @RequestBody UserPreferenceReq userPreferenceReq) {
        String userId = JwtTokenUtil.getUserIdByAuthorHead(headerValue);
        return userPreferenceService.updateNoticeByUserId(userId, userPreferenceReq);
    }

    @PostMapping(value = "/profile/show", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult showProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue) {
        return userProfileService.getUserProfileById(JwtTokenUtil.getUserIdByAuthorHead(headerValue));
    }

    @PostMapping(value = "/profile/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public RespResult updateProfile(@RequestHeader(name = JwtTokenUtil.AUTH_HEADER_KEY) String headerValue,
                                    @RequestBody UserProfileReq userProfileReq) {
        return userProfileService.updateProfileByUserId(JwtTokenUtil.getUserIdByAuthorHead(headerValue), userProfileReq);
    }
}
