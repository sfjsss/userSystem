package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.pojo.UserPreference;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.req.UserPreferenceReq;
import org.springframework.stereotype.Service;

@Service
public interface UserPreferenceService extends BaseService<UserPreference, String> {
    RespResult getNoticeByUserId(String userId);

    RespResult updateNoticeByUserId(String userId, UserPreferenceReq userPreferenceReq);
}
