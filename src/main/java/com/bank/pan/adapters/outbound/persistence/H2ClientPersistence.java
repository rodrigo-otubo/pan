package com.bank.pan.adapters.outbound.persistence;

import com.bank.pan.adapters.outbound.persistence.entity.ClientEntity;
import com.bank.pan.adapters.outbound.persistence.repository.ClientRepository;
import com.bank.pan.application.port.outbound.ClientPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class H2ClientPersistence implements ClientPersistencePort {

    private final ClientRepository clientRepository;

    public H2ClientPersistence(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return this.clientRepository.save(clientEntity);
    }

    @Override
    public ClientEntity get(String cpf) {
        //TODO fazer exceptions
        return this.clientRepository.findByCpf(cpf).orElseThrow();
    }

    @Override
    public List<ClientEntity> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public ClientEntity update(ClientEntity clientEntity) {
        return this.clientRepository.save(clientEntity);
    }

    @Override
    public void delete(ClientEntity clientEntity) {
        this.clientRepository.delete(clientEntity);
    }
}
