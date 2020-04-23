package com.serviceorder.api.serviceorderapi.domain.service;

import com.serviceorder.api.serviceorderapi.domain.exception.RuleException;
import com.serviceorder.api.serviceorderapi.domain.model.Client;
import com.serviceorder.api.serviceorderapi.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrudClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client){
        Client existingClient = clientRepository.findByEmail(client.getEmail());

        if (existingClient != null && !existingClient.equals(client)){
            throw new RuleException("Já existe um cliente cadastrado com este email");
        }

        return clientRepository.save(client);
    }

    public void delete(Long clientId){
        clientRepository.deleteById(clientId);
    }
}
