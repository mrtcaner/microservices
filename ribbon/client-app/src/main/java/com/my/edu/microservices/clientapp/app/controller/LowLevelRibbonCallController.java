package com.my.edu.microservices.clientapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    public String getClientName(){
        ServiceInstance instance = loadBalancerClient.choose("otherclient");
        URI clientURI = URI.create(String.format("httop://%s:%s",
                instance.getHost(),instance.getPort()));

        return (new RestTemplate()).getForObject(clientURI + "/name",String.class);
    }
}
