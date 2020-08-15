package com.tianyuli.usersystem.service;

import org.springframework.stereotype.Service;

@Service
public interface RegisterRecordService {

    String getCaptchaByUsername(String username) throws NullPointerException;
}
