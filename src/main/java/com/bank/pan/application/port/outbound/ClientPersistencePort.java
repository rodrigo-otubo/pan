package com.bank.pan.application.port.outbound;

import com.bank.pan.application.domain.ClientDomain;

import java.util.List;

public interface ClientPersistencePort {
    ClientDomain save(ClientDomain clientDomain);

    ClientDomain get(String cpf);

    List<ClientDomain> getAll();

    ClientDomain update(ClientDomain clientDomain);

    void delete(ClientDomain clientDomain);

    ClientDomain getById(Integer id);
}
