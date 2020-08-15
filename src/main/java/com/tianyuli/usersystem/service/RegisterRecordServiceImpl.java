package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.RegisterRecordDao;
import com.tianyuli.usersystem.pojo.RegisterRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterRecordServiceImpl extends BaseServiceImpl<RegisterRecord, String> implements RegisterRecordService {

    @Autowired
    private RegisterRecordDao registerRecordDao;

    @Override
    public String getCaptchaByUsername(String username) throws NullPointerException {
        RegisterRecord registerRecord = registerRecordDao.findByUsername(username);
        return registerRecord.getCaptcha();
    }

    @Override
    protected JpaRepository getJpaRepository() {
        return this.registerRecordDao;
    }
}
