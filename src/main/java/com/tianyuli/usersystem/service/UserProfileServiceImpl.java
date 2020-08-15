package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserProfileDao;
import com.tianyuli.usersystem.pojo.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile, String> implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.userProfileDao;
    }
}
