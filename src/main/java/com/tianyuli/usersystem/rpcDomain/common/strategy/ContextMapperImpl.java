package com.tianyuli.usersystem.rpcDomain.common.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextMapperImpl implements ContextMapper {

    @Autowired
    private RegisterProcessingStrategyImpl registerProcessingStrategy;

    @Override
    public UserStrategy loadProcessor(OperatorStrategyEnum operatorStrategyEnum) {
        if (operatorStrategyEnum == OperatorStrategyEnum.SUCCESS || operatorStrategyEnum == OperatorStrategyEnum.FAIL) {
            return this.registerProcessingStrategy;
        }
        return null;
    }
}
