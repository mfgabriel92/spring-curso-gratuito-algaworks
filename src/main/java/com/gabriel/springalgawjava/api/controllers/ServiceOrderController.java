package com.gabriel.springalgawjava.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.gabriel.springalgawjava.api.models.ServiceOrderModel;
import com.gabriel.springalgawjava.api.models.ServiceOrderRequest;
import com.gabriel.springalgawjava.domain.models.ServiceOrder;
import com.gabriel.springalgawjava.domain.repositories.ServiceOrderRepository;
import com.gabriel.springalgawjava.domain.services.ServiceOrderService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/service-orders")
public class ServiceOrderController {
    @Autowired
    private ServiceOrderService service;

    @Autowired
    private ServiceOrderRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ServiceOrderModel> findAll() {
        return toListModel(repository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ServiceOrderModel> findById(@PathVariable Integer id) {
        Optional<ServiceOrder> serviceOrder = repository.findById(id);

        if (!serviceOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        ServiceOrderModel serviceOrderModel = toModel(serviceOrder.get());
        return ResponseEntity.ok(serviceOrderModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrderModel save(@Valid @RequestBody ServiceOrderRequest serviceOrderRequest) {
        ServiceOrder serviceOrder = modelMapper.map(serviceOrderRequest, ServiceOrder.class);
        return toModel(service.save(serviceOrder));
    }

    @PutMapping("{id}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeOrder(@PathVariable Integer id) {
        service.closeOrder(id);
    }

    private ServiceOrderModel toModel(ServiceOrder serviceOrder) {
        return modelMapper.map(serviceOrder, ServiceOrderModel.class);
    }

    private List<ServiceOrderModel> toListModel(List<ServiceOrder> serviceOrders) {
        return serviceOrders
            .stream()
            .map(serviceOrder -> toModel(serviceOrder))
            .collect(Collectors.toList());
    }
}