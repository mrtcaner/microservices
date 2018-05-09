package com.my.edu.microservices.clientapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
