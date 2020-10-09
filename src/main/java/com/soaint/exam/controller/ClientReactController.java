package com.soaint.exam.controller;

import com.soaint.exam.entities.Client;
import com.soaint.exam.service.ClientServiceImpl;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/rx")
public class ClientReactController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping(
            value = "/clients/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<Client> getClientById(@PathVariable(value = "id") Long id) {
        return clientService.getClientDetail(id)
                .subscribeOn(Schedulers.io());
    }
}
