package com.tianyuli.usersystem.dao;

import com.tianyuli.usersystem.pojo.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, String> {
}
