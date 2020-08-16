package com.tianyuli.usersystem.rpcDomain.common.component.validate;

import com.tianyuli.usersystem.rpcDomain.common.exception.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReqValidateManagerImpl<T> implements ReqValidateManager<T> {

    @Autowired
    private List<FormValidator> validators;

    @Override
    public void doExecute(T arg) throws ValidateException {
        for (FormValidator validator : validators) {
            if (validator.canAccept(arg)) {
                validator.validate(arg);
            }
        }
    }
}
