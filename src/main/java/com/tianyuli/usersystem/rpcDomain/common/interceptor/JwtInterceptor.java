package com.tianyuli.usersystem.rpcDomain.common.interceptor;

import com.tianyuli.usersystem.rpcDomain.common.ResultCode;
import com.tianyuli.usersystem.rpcDomain.common.exception.CustomException;
import com.tianyuli.usersystem.rpcDomain.common.token.JwtIgnore;
import com.tianyuli.usersystem.rpcDomain.common.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore annotation = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (annotation != null) {
                return true;
            }
        }
        String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        if (StringUtils.isBlank(authHeader) || authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.info("user is not logged in");
            throw new CustomException(ResultCode.USER_NOT_LOGGED_IN);
        }
        String token = authHeader.substring(7);
        if (JwtTokenUtil.isExpiration(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.info("token has expired");
            return false;
        }
        return true;

    }
}
