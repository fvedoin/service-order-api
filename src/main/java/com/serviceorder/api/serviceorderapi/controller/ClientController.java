package com.serviceorder.api.serviceorderapi.controller;

import com.serviceorder.api.serviceorderapi.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @GetMapping("/clients")
    public List<Client> getAll(){
        var client = new Client();
        client.setId(1L);
        client.setName("test name");
        client.setEmail("test@email.com");
        client.setPhone("55 99999-9999");

        return Arrays.asList(client);
    }
}
