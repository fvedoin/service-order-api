package com.serviceorder.api.serviceorderapi.domain.service;

import com.serviceorder.api.serviceorderapi.domain.exception.RuleException;
import com.serviceorder.api.serviceorderapi.domain.model.Client;
import com.serviceorder.api.serviceorderapi.domain.model.ServiceOrder;
import com.serviceorder.api.serviceorderapi.domain.model.ServiceOrderStatus;
import com.serviceorder.api.serviceorderapi.domain.repository.ClientRepository;
import com.serviceorder.api.serviceorderapi.domain.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class CrudServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ServiceOrder store(ServiceOrder serviceOrder){
        Client client = clientRepository.findById(serviceOrder.getClient().getId())
                .orElseThrow(() -> new RuleException("Client not found"));

        serviceOrder.setClient(client);
        serviceOrder.setStatus(ServiceOrderStatus.OPEN);
        serviceOrder.setStartDate(OffsetDateTime.now());

        return serviceOrderRepository.save(serviceOrder);
    }
}
