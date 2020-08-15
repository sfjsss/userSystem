package com.tianyuli.usersystem.dao;

import com.tianyuli.usersystem.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    User getByUsername(String username);
}
