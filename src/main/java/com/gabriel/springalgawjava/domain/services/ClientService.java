package com.gabriel.springalgawjava.domain.services;

import com.gabriel.springalgawjava.domain.exceptions.DuplicatedResourceException;
import com.gabriel.springalgawjava.domain.models.Client;
import com.gabriel.springalgawjava.domain.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client save(Client client) {
        Client existingClient = repository.findByEmail(client.getEmail());

        if (existingClient != null && !existingClient.equals(client)) {
            throw new DuplicatedResourceException("The e-mail you are trying to use is already taken by someone else");
        } 

        return repository.save(client);
    }
}