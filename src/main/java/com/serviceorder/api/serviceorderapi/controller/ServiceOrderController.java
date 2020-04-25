package com.serviceorder.api.serviceorderapi.controller;

import com.serviceorder.api.serviceorderapi.api.model.ServiceOrderInputModel;
import com.serviceorder.api.serviceorderapi.api.model.ServiceOrderModel;
import com.serviceorder.api.serviceorderapi.domain.model.ServiceOrder;
import com.serviceorder.api.serviceorderapi.domain.repository.ServiceOrderRepository;
import com.serviceorder.api.serviceorderapi.domain.service.CrudServiceOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service-order")
public class ServiceOrderController {

    @Autowired
    private CrudServiceOrderService crudServiceOrderService;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrderModel store(@Valid @RequestBody ServiceOrderInputModel serviceOrderInputModel){
        ServiceOrder serviceOrder = toEntity(serviceOrderInputModel);

        return toModel(crudServiceOrderService.store(serviceOrder));
    }

    @GetMapping
    public List<ServiceOrderModel> getAll(){
        return toCollectionModel(serviceOrderRepository.findAll());
    }

    @GetMapping("/{serviceOrderId}")
    public ResponseEntity<ServiceOrderModel> search(@PathVariable Long serviceOrderId){
        Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(serviceOrderId);
        if(serviceOrder.isPresent()){
            ServiceOrderModel serviceOrderModel = toModel(serviceOrder.get());
            return ResponseEntity.ok(serviceOrderModel);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{serviceOrderId}/close")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void close(@PathVariable Long serviceOrderId){
        crudServiceOrderService.close(serviceOrderId);
    }

    @PutMapping("/{serviceOrderId}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long serviceOrderId){
        crudServiceOrderService.cancel(serviceOrderId);
    }

    private ServiceOrderModel toModel(ServiceOrder serviceOrder){
        return modelMapper.map(serviceOrder, ServiceOrderModel.class);
    }

    private List<ServiceOrderModel> toCollectionModel(List<ServiceOrder> serviceOrder){
        return serviceOrder.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private ServiceOrder toEntity(ServiceOrderInputModel serviceOrderInputModel){
        return modelMapper.map(serviceOrderInputModel, ServiceOrder.class);
    }
}
