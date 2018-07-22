package com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.impl;

import com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.IExecutionStrategy;

/**
 * Created by lenovo510 on 14.05.2018.
 */
public class ReturnAfterTwentySeconds implements IExecutionStrategy {
    @Override
    public String execute() {
        try {
            Thread.sleep(20000);
            return "Twenty Seconds passed!";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Something went wrong while waitin for 20 seconds!";
    }

    @Override
    public String getExecutionStrategyName() {
        return "ReturnAfterTwentySeconds";
    }
}
