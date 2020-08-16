package com.tianyuli.usersystem.rpcDomain.common.exception;

import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandle {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @ExceptionHandler
    @ResponseBody
    public RespResult handleException(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        logger.info(e.getMessage());
        if (e instanceof ValidateException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new RespResult(((ValidateException) e).resultCode);
        }

        return new RespResult(ResultCode.SERVER_ERROR);
    }
}
