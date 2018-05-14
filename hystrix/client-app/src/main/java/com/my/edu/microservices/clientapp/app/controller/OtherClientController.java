package com.my.edu.microservices.clientapp.app.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by lenovo510 on 9.05.2018.
 */
@FeignClient("otherclient")
public interface OtherClientController {

    @GetMapping(value = "/name")
    public String getOtherClientsName();

    @GetMapping(value = "/randomfail")
    public String callRandomFailingMethod();

    @GetMapping(value = "/alwaysfail")
    public String callAlwaysFailingMethod();

    @GetMapping(value = "/failforfivetimes")
    public String callFailForFiveTimesMethod();

    @GetMapping(value = "/executestrategy1")
    public String executestrategy1();

    @GetMapping(value = "/executestrategy2")
    public String executestrategy2();

    @GetMapping(value = "/executestrategy3")
    public String executestrategy3();

    @GetMapping(value = "/executestrategy4")
    public String executestrategy4();

    @GetMapping(value = "/executestrategy5")
    public String executestrategy5();



}
