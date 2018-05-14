package com.my.edu.microservices.otherclientapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by lenovo510 on 3.05.2018.
 */
@RestController
public class OtherClientController {

    private static String id = UUID.randomUUID().toString();

    public static int callCount =0;

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/name")
    public String getClientName(){
        return "Hi from Other Client with id: " + id;
    }

    @GetMapping(value = "/client")
    public Map getOtherClientsProperties(){
        Map otherClientProps = new HashMap<>();
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("client");
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

    @GetMapping(value = "/clientsname")
    public String getOtherClientsName(){
        List<String> otherClientNames = new ArrayList<>();
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("client");
        URI otherClientURI = serviceInstances.get(0).getUri();
        if(otherClientURI != null){
            return (new RestTemplate()).getForObject(otherClientURI + "/name",String.class);
        }

        return null;

    }

    @GetMapping(value = "/randomfail")
    public String callRandomFailingMethod(){
        if(ThreadLocalRandom.current().nextBoolean()){
            throw new RuntimeException();
        }else{
            return "Lucky you!";
        }
    }

    @GetMapping(value = "/alwaysfail")
    public String callAlwaysFailingMethod(){
        throw new RuntimeException();
    }

    @GetMapping(value = "/failforfivetimes")
    public String callFailForFiveTimesMethod(){
        if(callCount< 5){
            callCount++;
            throw new RuntimeException();
        }else{
            callCount = 0;
            return "6th time is the charm, right!";
        }
    }
}
