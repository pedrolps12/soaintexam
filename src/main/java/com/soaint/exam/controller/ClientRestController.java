package com.soaint.exam.controller;

import com.soaint.exam.entities.Client;
import com.soaint.exam.model.ErrorObject;
import com.soaint.exam.repository.ClientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@Log4j2
public class ClientRestController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Client _client = clientRepository
                    .save(Client.builder().firstname(client.getFirstname()).lastname(client.getLastname()).dni(client.getDni()).email(client.getEmail()).telefono(client.getTelefono()).build());
            return new ResponseEntity<>(_client, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("{}", ErrorObject.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error al grabar el cliente").backendMessage(e.getMessage()).build().toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

