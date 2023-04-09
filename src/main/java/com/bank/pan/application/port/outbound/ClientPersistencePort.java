package com.bank.pan.application.port.outbound;

import com.bank.pan.adapters.outbound.persistence.entity.ClientEntity;
import com.bank.pan.application.domain.ClientDomain;

import java.util.List;

public interface ClientPersistencePort {
    ClientEntity save(ClientEntity clientEntity);

    ClientEntity get(String cpf);

    List<ClientEntity> getAll();

    ClientEntity update(ClientEntity clientEntity);

    void delete(ClientEntity clientEntity);
}
