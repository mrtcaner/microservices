package com.my.edu.microservices.clientapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Created by lenovo510 on 9.05.2018.
 */
@RestController
public class ClientController {

    @Autowired
    OtherClientController otherClientController;

    @GetMapping(value = "/name")
    public String getClientName() {
        return "Greetings from Client!";
    }

    @GetMapping(value = "/otherclientsname")
    public String getOtherClientsName() {
        return otherClientController.getOtherClientsName();
    }

    @HystrixCommand(fallbackMethod = "randomFailFallBack")
    @GetMapping(value = "/randomfail")
    public String callRandomFailingMethod() {
        return otherClientController.callRandomFailingMethod();
    }

    public String randomFailFallBack(){
        return "call to randomfail method failed!";
    }

    @HystrixCommand(fallbackMethod = "alwaysFailFallBack")
    @GetMapping(value = "/alwaysfail")
    public String callAlwaysFailingMethod() {
        return otherClientController.callAlwaysFailingMethod();
    }

    public String alwaysFailFallBack(){
        return "call to alwaysfail method failed!";
    }

    @HystrixCommand(fallbackMethod = "failforfivetimesFallBack")
    @GetMapping(value = "/failforfivetimes")
    public String callFailForFiveTimesMethod() {
        return otherClientController.callFailForFiveTimesMethod();
    }

    public String failforfivetimesFallBack(){
        return "call to failforfivetimes method failed!";
    }

    @HystrixCommand(fallbackMethod = "executestrategy1FallBack")
    @GetMapping(value = "/executestrategy1")
    public String failbystrategy1(){
        return otherClientController.executestrategy1();
    }

    public String executestrategy1FallBack(){
        return "strategy1 failded to execute, fallback ran!";
    }

    @HystrixCommand(fallbackMethod = "failbystrategy2FallBack")
    @GetMapping(value = "/executestrategy2")
    public String failbystrategy2(){
        return otherClientController.executestrategy2();
    }

    public String failbystrategy2FallBack(){
        return "strategy2 failded to execute, fallback ran!";
    }

    @HystrixCommand(fallbackMethod = "failbystrategy3FallBack")
    @GetMapping(value = "/executestrategy3")
    public String failbystrategy3(){
        return otherClientController.executestrategy3();
    }

    public String failbystrategy3FallBack(){
        return "strategy3 failded to execute, fallback ran!";
    }

    @HystrixCommand(fallbackMethod = "failbystrategy4FallBack")
    @GetMapping(value = "/executestrategy4")
    public String failbystrategy4(){
        return otherClientController.executestrategy4();
    }

    public String failbystrategy4FallBack(){
        return "strategy4 failded to execute, fallback ran!";
    }

    @HystrixCommand(fallbackMethod = "failbystrategy5FallBack")
    @GetMapping(value = "/executestrategy5")
    public String failbystrategy5(){
        return otherClientController.executestrategy5();
    }

    public String failbystrategy5FallBack(){
        return "strategy5 failded to execute, fallback ran!";
    }
}
