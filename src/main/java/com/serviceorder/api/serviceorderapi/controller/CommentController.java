package com.serviceorder.api.serviceorderapi.controller;

import com.serviceorder.api.serviceorderapi.api.model.CommentInputModel;
import com.serviceorder.api.serviceorderapi.api.model.CommentModel;
import com.serviceorder.api.serviceorderapi.domain.exception.EntityNotFoundException;
import com.serviceorder.api.serviceorderapi.domain.model.Comment;
import com.serviceorder.api.serviceorderapi.domain.model.ServiceOrder;
import com.serviceorder.api.serviceorderapi.domain.repository.CommentRepository;
import com.serviceorder.api.serviceorderapi.domain.repository.ServiceOrderRepository;
import com.serviceorder.api.serviceorderapi.domain.service.CrudServiceOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service-order/{serviceOrderId}/comments")
public class CommentController {

    @Autowired
    private CrudServiceOrderService crudServiceOrderService;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CommentModel> getAll(@PathVariable Long serviceOrderId){
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Service order not found"));

        return toCollectionModel(serviceOrder.getComments());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentModel store(@PathVariable Long serviceOrderId, @Valid @RequestBody CommentInputModel commentInputModel){
        Comment comment = crudServiceOrderService.addComment(serviceOrderId, commentInputModel.getDescription());

        return toModel(comment);
    }

    private CommentModel toModel(Comment comment){
        return modelMapper.map(comment, CommentModel.class);
    }

    private List<CommentModel> toCollectionModel(List<Comment> comments){
        return comments.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
