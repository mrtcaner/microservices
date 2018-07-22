package com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.impl;

import com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.IExecutionStrategy;

/**
 * Created by lenovo510 on 14.05.2018.
 */
public class FailFiveOutOfSix implements IExecutionStrategy{

    private static int callCount = 0;

    @Override
    public String execute() {
        if(callCount < 5){
            callCount++;
            throw new RuntimeException();
        }else{
            callCount = 0;
            return "6th time is the charm, right!";
        }
    }

    @Override
    public String getExecutionStrategyName() {
        return "FailFiveOutOfSix";
    }

}
