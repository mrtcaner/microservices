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
}
