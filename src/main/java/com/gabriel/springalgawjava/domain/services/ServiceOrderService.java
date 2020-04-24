package com.gabriel.springalgawjava.domain.services;

import java.time.OffsetDateTime;

import com.gabriel.springalgawjava.domain.exceptions.ResourceException;
import com.gabriel.springalgawjava.domain.exceptions.ResourceNotFoundException;
import com.gabriel.springalgawjava.domain.models.Client;
import com.gabriel.springalgawjava.domain.models.Comment;
import com.gabriel.springalgawjava.domain.models.ServiceOrder;
import com.gabriel.springalgawjava.domain.models.ServiceOrderStatus;
import com.gabriel.springalgawjava.domain.repositories.CommentRepository;
import com.gabriel.springalgawjava.domain.repositories.ServiceOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrderService {
    @Autowired
    private ServiceOrderRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CommentRepository commentRepository;

    public ServiceOrder findById(Integer id) {
        return repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Service order not found"));
    }

    public ServiceOrder save(ServiceOrder serviceOrder) {
        Client client = clientService.findById(serviceOrder.getClient().getId());

        serviceOrder.setClient(client);
        serviceOrder.setStatus(ServiceOrderStatus.OPEN);
        serviceOrder.setOpeningDate(OffsetDateTime.now());

        return repository.save(serviceOrder);
    }

    public void closeOrder(Integer id) {
        ServiceOrder serviceOrder = findById(id);

        if (!serviceOrder.getStatus().equals(ServiceOrderStatus.OPEN)) {
            throw new ResourceException("Only open orders can be closed");
        }
        
        serviceOrder.setStatus(ServiceOrderStatus.FINALIZED);
        serviceOrder.setClosingDate(OffsetDateTime.now());

        repository.save(serviceOrder);
    }

    public Comment comment(Integer id, String content) {
        ServiceOrder order = findById(id);

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreatedAt(OffsetDateTime.now());
        comment.setServiceOrder(order);

        return commentRepository.save(comment);
    }
}