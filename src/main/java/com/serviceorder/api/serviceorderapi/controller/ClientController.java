package com.serviceorder.api.serviceorderapi.controller;

import com.serviceorder.api.serviceorderapi.domain.model.Client;
import com.serviceorder.api.serviceorderapi.domain.repository.ClientRepository;
import com.serviceorder.api.serviceorderapi.domain.service.CrudClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CrudClientService crudClientService;

    @GetMapping()
    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> search(@PathVariable Long clientId){
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isPresent()){
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client store(@Valid @RequestBody Client client){
        return crudClientService.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@Valid @PathVariable long clientId, @RequestBody  Client client){
        if(!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }

        client.setId(clientId);
        client = crudClientService.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable long clientId){
        if(!clientRepository.existsById(clientId)){
            return ResponseEntity.notFound().build();
        }

        crudClientService.delete(clientId);

        return ResponseEntity.noContent().build();
    }
}
