package com.gabriel.springalgawjava.domain.repositories;

import com.gabriel.springalgawjava.domain.models.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByEmail(String email);
}