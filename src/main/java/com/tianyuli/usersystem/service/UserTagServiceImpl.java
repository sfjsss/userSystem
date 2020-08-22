package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.UserTagDao;
import com.tianyuli.usersystem.pojo.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTagServiceImpl extends BaseServiceImpl<UserTag, String> implements UserTagService {

    @Autowired
    private UserTagDao userTagDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.userTagDao;
    }

    @Override
    public List<String> getUserTagList(String userId) {
        return userTagDao.getUserTagList(userId);
    }
}
