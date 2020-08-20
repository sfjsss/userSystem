package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserProfileDao;
import com.tianyuli.usersystem.pojo.Address;
import com.tianyuli.usersystem.pojo.User;
import com.tianyuli.usersystem.pojo.UserPreference;
import com.tianyuli.usersystem.pojo.UserProfile;
import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.req.UserProfileReq;
import com.tianyuli.usersystem.rpcDomain.resp.UserProfileResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String> implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserPreferenceService userPreferenceService;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.userProfileDao;
    }

    @Override
    public RespResult updateProfileByUserId(String userIdByAuthorHead, UserProfileReq userProfileReq) {
        Optional<User> user = userService.findById(userIdByAuthorHead);
        if (!user.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        User foundUser = user.get();
        Address address = addressService.findById(foundUser.getId()).get();
        UserPreference userPreference = userPreferenceService.findById(foundUser.getId()).get();
        UserProfile userProfile = userProfileDao.findById(foundUser.getId()).get();

        BeanUtils.copyProperties(userProfileReq, foundUser);
        BeanUtils.copyProperties(userProfileReq, address);
        BeanUtils.copyProperties(userProfileReq, userPreference);
        BeanUtils.copyProperties(userProfileReq, userProfile);

        userService.save(foundUser);
        addressService.save(address);
        userPreferenceService.save(userPreference);
        userProfileDao.save(userProfile);

        return new RespResult(ResultCode.SUCCESS);
    }

    @Override
    public RespResult getUserProfileById(String userIdByAuthorHead) {
        Optional<User> user = userService.findById(userIdByAuthorHead);
        if (!user.isPresent()) {
            return new RespResult(ResultCode.USER_NOT_EXIST);
        }
        User foundUser = user.get();
        Address address = addressService.findById(foundUser.getId()).get();
        UserPreference userPreference = userPreferenceService.findById(foundUser.getId()).get();
        UserProfile userProfile = userProfileDao.findById(foundUser.getId()).get();
        UserProfileResp userProfileResp = new UserProfileResp();

        BeanUtils.copyProperties(foundUser, userProfileResp);
        BeanUtils.copyProperties(address, userProfileResp);
        BeanUtils.copyProperties(userPreference, userProfileResp);
        BeanUtils.copyProperties(userProfile, userProfileResp);

        return new RespResult(ResultCode.SUCCESS, userProfileResp);
    }
}
