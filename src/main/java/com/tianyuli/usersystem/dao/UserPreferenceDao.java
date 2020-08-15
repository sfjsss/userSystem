package com.tianyuli.usersystem.dao;

import com.tianyuli.usersystem.pojo.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPreferenceDao extends JpaRepository<UserPreference, String> {
}
