package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserTagDao;
import com.tianyuli.usersystem.pojo.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserTagServiceImpl extends BaseServiceImpl<UserTag, String> implements UserTagService {

    @Autowired
    private UserTagDao userTagDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.userTagDao;
    }
}
