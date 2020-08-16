package com.tianyuli.usersystem.rpcDomain.common.component.validate;

import com.tianyuli.usersystem.rpcDomain.common.exception.ValidateException;

public interface FormValidator<T> {

    boolean canAccept(T arg);

    void validate(T arg) throws ValidateException;
}
