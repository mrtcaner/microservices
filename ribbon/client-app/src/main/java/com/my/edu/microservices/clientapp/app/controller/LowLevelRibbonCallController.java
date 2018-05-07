package com.my.edu.microservices.clientapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by lenovo510 on 5.05.2018.
 */
@RestController
public class LowLevelRibbonCallController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/otherclientsnamelowlevel")
    public String getClientName(){
        ServiceInstance instance = loadBalancerClient.choose("otherclient");
        URI clientURI = URI.create(String.format("http://%s:%s",
                instance.getHost(),instance.getPort()));

        return (new RestTemplate()).getForObject(clientURI + "/name",String.class);
    }
}
