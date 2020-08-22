package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.pojo.UserTag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserTagService extends BaseService<UserTag, String> {
    List<String> getUserTagList(String userId);
}
