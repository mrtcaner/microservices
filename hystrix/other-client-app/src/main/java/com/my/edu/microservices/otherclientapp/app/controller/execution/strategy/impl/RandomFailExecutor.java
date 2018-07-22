package com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.impl;

import com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.IExecutionStrategy;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by lenovo510 on 14.05.2018.
 */
public class RandomFailExecutor implements IExecutionStrategy {
    @Override
    public String execute() {
        if(ThreadLocalRandom.current().nextBoolean()){
            throw new RuntimeException();
        }else{
            return "Lucky you!";
        }
    }

    @Override
    public String getExecutionStrategyName() {
        return "RandomFailExecutor";
    }
}
