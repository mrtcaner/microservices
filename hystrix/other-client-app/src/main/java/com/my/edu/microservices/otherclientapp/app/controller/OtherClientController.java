package com.my.edu.microservices.otherclientapp.app.controller;

import com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.IExecutionStrategy;
import com.my.edu.microservices.otherclientapp.app.controller.execution.strategy.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by lenovo510 on 3.05.2018.
 */
@RestController
public class OtherClientController {

    private static String id = UUID.randomUUID().toString();

    public static int callCount = 0;

    private IExecutionStrategy failbystrategy1;
    private IExecutionStrategy failbystrategy2;
    private IExecutionStrategy failbystrategy3;
    private IExecutionStrategy failbystrategy4;

    List<IExecutionStrategy> strategies = new ArrayList<>();

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostConstruct
    public void init() {
        List<IExecutionStrategy> baseStrategies = new ArrayList<>();
        baseStrategies.add(new AlwaysFailExecutor());
        baseStrategies.add(new FailFiveOutOfSix());
        baseStrategies.add(new RandomFailExecutor());
        baseStrategies.add(new ReturnAfterTwentySeconds());
        baseStrategies.add(new SuccessfulExecutor());

        for (int i = 0; i < 5; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, baseStrategies.size());
            int j = 0;
            Iterator<IExecutionStrategy> it = baseStrategies.iterator();
            while (j <= index) {
                IExecutionStrategy executionStrategy = it.next();
                if (j == index) {
                    strategies.add(executionStrategy);
                    it.remove();
                }
                j++;
            }
        }

        int index = 1;
        for(IExecutionStrategy executionStrategy : strategies){
            System.err.println("executestrategy" + index++ + ":" + executionStrategy.getExecutionStrategyName());
        }


    }

    @GetMapping(value = "/name")
    public String getClientName() {
        return "Hi from Other Client with id: " + id;
    }

    @GetMapping(value = "/client")
    public Map getOtherClientsProperties() {
        Map otherClientProps = new HashMap<>();
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("client");
        if (!CollectionUtils.isEmpty(serviceInstances)) {
            otherClientProps.put("uri", serviceInstances.get(0).getUri());
            otherClientProps.put("host", serviceInstances.get(0).getHost());
            otherClientProps.put("port", serviceInstances.get(0).getPort());
            otherClientProps.put("metaData", serviceInstances.get(0).getMetadata());
            otherClientProps.put("serviceId", serviceInstances.get(0).getServiceId());
            otherClientProps.put("schema", serviceInstances.get(0).getScheme());

        }
        return otherClientProps;
    }

    @GetMapping(value = "/clientsname")
    public String getOtherClientsName() {
        List<String> otherClientNames = new ArrayList<>();
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("client");
        URI otherClientURI = serviceInstances.get(0).getUri();
        if (otherClientURI != null) {
            return (new RestTemplate()).getForObject(otherClientURI + "/name", String.class);
        }

        return null;

    }

    @GetMapping(value = "/randomfail")
    public String callRandomFailingMethod() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            throw new RuntimeException();
        } else {
            return "Lucky you!";
        }
    }

    @GetMapping(value = "/alwaysfail")
    public String callAlwaysFailingMethod() {
        throw new RuntimeException();
    }

    @GetMapping(value = "/failforfivetimes")
    public String callFailForFiveTimesMethod() {
        if (callCount < 5) {
            callCount++;
            throw new RuntimeException();
        } else {
            callCount = 0;
            return "6th time is the charm, right!";
        }
    }

    @GetMapping(value = "/executestrategy1")
    public String executestrategy1() {
        return strategies.get(0).execute();
    }

    @GetMapping(value = "/executestrategy2")
    public String executestrategy2() {
        return strategies.get(1).execute();
    }

    @GetMapping(value = "/executestrategy3")
    public String executestrategy3() {
        return strategies.get(2).execute();
    }

    @GetMapping(value = "/executestrategy4")
    public String executestrategy4() {
        return strategies.get(3).execute();
    }

    @GetMapping(value = "/executestrategy5")
    public String executestrategy5() {
        return strategies.get(4).execute();
    }

}
