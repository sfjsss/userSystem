package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.pojo.UserProfile;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.req.UserProfileReq;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService extends BaseService<UserProfile, String> {
    RespResult getUserProfileById(String userIdByAuthorHead);

    RespResult updateProfileByUserId(String userIdByAuthorHead, UserProfileReq userProfileReq);
}
