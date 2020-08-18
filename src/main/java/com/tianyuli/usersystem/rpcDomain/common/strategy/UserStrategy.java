package com.tianyuli.usersystem.rpcDomain.common.strategy;

import com.tianyuli.usersystem.rpcDomain.common.RespResult;
import com.tianyuli.usersystem.rpcDomain.req.RegisterRequest;

public interface UserStrategy {

    RespResult doProcessor(RegisterRequest registerRequest, OperatorStrategyEnum operatorStrategyEnum);
}
