package com.tianyuli.usersystem.rpcDomain.common.strategy;

import org.springframework.stereotype.Service;

@Service
public interface ContextMapper {

    UserStrategy loadProcessor(OperatorStrategyEnum operatorStrategyEnum);
}
