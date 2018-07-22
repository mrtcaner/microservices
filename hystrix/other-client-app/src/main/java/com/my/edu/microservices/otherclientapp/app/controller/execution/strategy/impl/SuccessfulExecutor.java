package com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.impl;

import com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.IExecutionStrategy;

/**
 * Created by lenovo510 on 15.05.2018.
 */
public class SuccessfulExecutor implements IExecutionStrategy {
    @Override
    public String execute() {
        return "Execution Successful!";
    }

    @Override
    public String getExecutionStrategyName() {
        return "SuccessfulExecutor";
    }
}
