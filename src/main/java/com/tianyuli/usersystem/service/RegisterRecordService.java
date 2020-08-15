package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.pojo.RegisterRecord;
import org.springframework.stereotype.Service;

@Service
public interface RegisterRecordService extends BaseService<RegisterRecord, String> {

    String getCaptchaByUsername(String username) throws NullPointerException;
}
