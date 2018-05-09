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

}
