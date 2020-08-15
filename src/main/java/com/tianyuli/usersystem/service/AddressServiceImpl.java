package com.tianyuli.usersystem.service;

import com.tianyuli.usersystem.dao.AddressDao;
import com.tianyuli.usersystem.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, String> implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    protected JpaRepository getJpaRepository() {
        return this.addressDao;
    }
}
