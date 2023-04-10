package com.bank.pan.application.service;

import com.bank.pan.application.domain.ClientDomain;
import com.bank.pan.application.port.inbound.ClientServicePort;
import com.bank.pan.application.port.outbound.ClientPersistencePort;

import java.util.List;

public class ClientService implements ClientServicePort {

    private final ClientPersistencePort clientPersistencePort;

    public ClientService(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public ClientDomain save(ClientDomain clientDomain) {
        return this.clientPersistencePort.save(clientDomain);
    }

    @Override
    public ClientDomain get(String cpf) {
        return this.clientPersistencePort.get(cpf);
    }

    @Override
    public ClientDomain update(ClientDomain clientDomain) {
        return this.clientPersistencePort.update(clientDomain);
    }

    @Override
    public void delete(String cpf) {
        var clientFound = this.clientPersistencePort.get(cpf);
        this.clientPersistencePort.delete(new ClientDomain(clientFound.getId()));
    }

    @Override
    public ClientDomain getById(Integer id) {
        return this.clientPersistencePort.getById(id);
    }
}
