package com.my.edu.microservices.clientapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo510 on 3.05.2018.
 */
@RestController
public class ClientController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/name")
    public String getClientName(){
        return "Greetings from Client!";
    }

    @GetMapping(value = "/otherclient")
    public Map getOtherClientsProperties(){
        Map otherClientProps = new HashMap<>();
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("otherclient");
        if(!CollectionUtils.isEmpty(serviceInstances)){
            otherClientProps.put("uri",serviceInstances.get(0).getUri());
            otherClientProps.put("host",serviceInstances.get(0).getHost());
            otherClientProps.put("port",serviceInstances.get(0).getPort());
            otherClientProps.put("metaData",serviceInstances.get(0).getMetadata());
            otherClientProps.put("serviceId",serviceInstances.get(0).getServiceId());
            otherClientProps.put("schema",serviceInstances.get(0).getScheme());

        }
        return otherClientProps;
    }

    @GetMapping(value = "/otherclientsname")
    public String getOtherClientsName(){
            return restTemplate.getForObject("http://" + "otherclient" + "/name", String.class);
            //return (new RestTemplate()).getForObject(otherClientURI + "/name",String.class);
    }
}
