package com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.impl;

import com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.IExecutionStrategy;

/**
 * Created by lenovo510 on 14.05.2018.
 */
public class AlwaysFailExecutor implements IExecutionStrategy {

    @Override
    public String execute() {
        throw new RuntimeException();
    }

    @Override
    public String getExecutionStrategyName() {
        return "AlwaysFailExecutor";
    }
}
