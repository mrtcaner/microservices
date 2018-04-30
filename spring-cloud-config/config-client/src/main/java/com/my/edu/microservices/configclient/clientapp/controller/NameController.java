package com.my.edu.microservices.configclient.clientapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo510 on 30.04.2018.
 */
@RestController
public class NameController {

    @Value("${prefixes}")
    private String prefixes;

    @GetMapping(value = "/prefixes")
    public String getAllPrefixes() {
        return prefixes;
    }
}
