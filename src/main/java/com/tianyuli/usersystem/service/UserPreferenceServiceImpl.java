package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserPreferenceDao;
import com.tianyuli.usersystem.pojo.UserPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPreferenceServiceImpl extends BaseServiceImpl<UserPreference, String> implements UserPreferenceService {

    @Autowired
    private UserPreferenceDao userPreferenceDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.userPreferenceDao;
    }
}
