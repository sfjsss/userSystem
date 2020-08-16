package com.tianyuli.usersystem.rpcDomain.common.component.validate;

import com.tianyuli.usersystem.rpcDomain.common.exception.ValidateException;

public interface ReqValidateManager<T> {

    void doExecute(T args) throws ValidateException;
}
