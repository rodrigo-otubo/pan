package com.bank.pan.application.service;

import com.bank.pan.adapters.outbound.persistence.entity.ClientEntity;
import com.bank.pan.application.domain.ClientDomain;
import com.bank.pan.application.port.inbound.ClientServicePort;
import com.bank.pan.application.port.outbound.ClientPersistencePort;

import java.util.List;
import java.util.stream.Collectors;

public class ClientService implements ClientServicePort {

    private final ClientPersistencePort clientPersistencePort;

    public ClientService(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public ClientDomain save(ClientDomain clientDomain) {
        var clientSaved = this.clientPersistencePort.save(new ClientEntity(clientDomain.getName(), clientDomain.getCpf()));
        return new ClientDomain(clientSaved.getId(), clientSaved.getName(), clientSaved.getCpf());
    }

    @Override
    public ClientDomain get(Integer id) {
        var clientFound = this.clientPersistencePort.get(id);
        return new ClientDomain(clientFound.getId(), clientFound.getName(), clientFound.getCpf());
    }

    @Override
    public List<ClientDomain> getAll() {
        var clientsFound = this.clientPersistencePort.getAll();

        return clientsFound
                .stream()
                .map(client -> new ClientDomain(client.getId(), client.getName(), client.getCpf()))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDomain update(ClientDomain clientDomain) {
        var clientSaved = this.clientPersistencePort.update(new ClientEntity(clientDomain.getId(), clientDomain.getName(), clientDomain.getCpf()));
        return new ClientDomain(clientSaved.getId(), clientSaved.getName(), clientDomain.getCpf());
    }

    @Override
    public void delete(Integer id) {
        this.clientPersistencePort.delete(new ClientEntity(id));
    }
}
