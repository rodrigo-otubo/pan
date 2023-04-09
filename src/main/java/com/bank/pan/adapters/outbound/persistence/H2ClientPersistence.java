package com.bank.pan.adapters.outbound.persistence;

import com.bank.pan.adapters.infra.error.exceptions.BadRequestException;
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
        return this.clientRepository.findByCpf(cpf).orElseThrow(()-> new BadRequestException("Client Not Found"));
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

    @Override
    public ClientEntity getById(Integer id) {
        return this.clientRepository.findById(id).orElseThrow(()-> new BadRequestException("Client Not Found"));
    }
}
