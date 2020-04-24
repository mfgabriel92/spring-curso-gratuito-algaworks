package com.gabriel.springalgawjava.domain.services;

import com.gabriel.springalgawjava.domain.exceptions.ResourceException;
import com.gabriel.springalgawjava.domain.exceptions.ResourceNotFoundException;
import com.gabriel.springalgawjava.domain.models.Client;
import com.gabriel.springalgawjava.domain.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client findById(Integer id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    public Client save(Client client) {
        Client existingClient = repository.findByEmail(client.getEmail());

        if (existingClient != null && !existingClient.equals(client)) {
            throw new ResourceException("The e-mail you are trying to use is already taken by someone else");
        } 

        return repository.save(client);
    }
}