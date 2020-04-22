package com.gabriel.springalgawjava.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.gabriel.springalgawjava.domain.models.Client;
import com.gabriel.springalgawjava.domain.repositories.ClientRepository;
import com.gabriel.springalgawjava.domain.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        Optional<Client> client = repository.findById(id);

        if (!client.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@Valid @RequestBody Client client) {
        return service.save(client);
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> update(@Valid @PathVariable Integer id, @RequestBody Client client) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        client.setId(id);
        client = repository.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}