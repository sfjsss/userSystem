package com.tianyuli.usersystem.dao;

import com.tianyuli.usersystem.pojo.RegisterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRecordDao extends JpaRepository<RegisterRecord, String> {

    RegisterRecord findByUsername(String username);
}
