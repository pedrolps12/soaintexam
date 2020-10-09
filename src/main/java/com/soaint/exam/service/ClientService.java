package com.soaint.exam.service;

import com.soaint.exam.entities.Client;
import io.reactivex.Single;

public interface ClientService {

    Single<Client> getClientDetail(Long id);
}
