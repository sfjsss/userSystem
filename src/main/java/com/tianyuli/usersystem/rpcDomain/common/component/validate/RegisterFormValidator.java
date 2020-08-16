package com.tianyuli.usersystem.rpcDomain.common.component.validate;

import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.common.exception.ValidateException;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class RegisterFormValidator implements FormValidator<RegisterRequest> {

    @Override
    public boolean canAccept(RegisterRequest arg) {
        if (arg instanceof RegisterRequest) {
            return true;
        }
        return false;
    }

    @Override
    public void validate(RegisterRequest arg) throws ValidateException {
        if (StringUtils.isBlank(arg.getUsername()) || StringUtils.isBlank(arg.getPassword()) || StringUtils.isBlank(arg.getEmail()) || StringUtils.isNotBlank(arg.getCaptcha())) {
            throw new ValidateException(ResultCode.REG_DATA_IS_WRONG);
        }
    }
}
