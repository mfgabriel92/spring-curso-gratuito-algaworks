package com.gabriel.springalgawjava.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.gabriel.springalgawjava.api.models.CommentModel;
import com.gabriel.springalgawjava.api.models.CommentRequest;
import com.gabriel.springalgawjava.domain.models.Comment;
import com.gabriel.springalgawjava.domain.models.ServiceOrder;
import com.gabriel.springalgawjava.domain.services.ServiceOrderService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/service-orders/{id}/comments")
public class CommentController {
    @Autowired
    private ServiceOrderService serviceOrderService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CommentModel> findAll(@PathVariable Integer id) {
        ServiceOrder serviceOrder = serviceOrderService.findById(id);
        return toListModel(serviceOrder.getComments());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentModel save(@PathVariable Integer id, @Valid @RequestBody CommentRequest commentRequest) {
        Comment comment = serviceOrderService.comment(id, commentRequest.getContent());
        return toModel(comment);
    }

    private CommentModel toModel(Comment comment) {
        return modelMapper.map(comment, CommentModel.class);
    }

    private List<CommentModel> toListModel(List<Comment> comments) {
        return comments
            .stream()
            .map(comment -> toModel(comment))
            .collect(Collectors.toList());
    }
}