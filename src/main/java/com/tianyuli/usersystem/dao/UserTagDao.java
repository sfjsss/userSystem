package com.tianyuli.usersystem.dao;

import com.tianyuli.usersystem.pojo.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagDao extends JpaRepository<UserTag, String> {
}
