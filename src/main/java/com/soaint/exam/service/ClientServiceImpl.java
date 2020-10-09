package com.soaint.exam.service;

import com.soaint.exam.entities.Client;
import com.soaint.exam.repository.ClientRepository;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Single<Client> getClientDetail(Long id) {
        return findClientDetailRepository(id);
    }

    private Single<Client> findClientDetailRepository(Long id) {
        return Single.create(singleSubscriber -> {
            Client client = clientRepository.findOne(id);
            if (client == null )
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                singleSubscriber.onSuccess(client);
            }
        });
    }

}
