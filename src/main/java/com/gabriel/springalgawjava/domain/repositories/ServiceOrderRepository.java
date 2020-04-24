package com.gabriel.springalgawjava.domain.repositories;

import com.gabriel.springalgawjava.domain.models.ServiceOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Integer> {
}