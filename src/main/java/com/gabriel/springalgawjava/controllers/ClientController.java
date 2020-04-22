package com.gabriel.springalgawjava.controllers;

import java.util.Arrays;
import java.util.List;

import com.gabriel.springalgawjava.domain.models.Client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    @GetMapping()
    public List<Client> getAll() {
        Client c1 = new Client();
        c1.setId(1);
        c1.setName("Gabriel Monteiro Fernandes");

        Client c2 = new Client();
        c2.setId(2);
        c2.setName("Jeryanne Jane Patayon Fernandes");

        return Arrays.asList(c1, c2);
    }
}