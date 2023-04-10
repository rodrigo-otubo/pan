package com.bank.pan.adapters.outbound.persistence;

import com.bank.pan.adapters.infra.error.exceptions.BadRequestException;
import com.bank.pan.adapters.outbound.persistence.entity.ClientEntity;
import com.bank.pan.adapters.outbound.persistence.repository.ClientRepository;
import com.bank.pan.application.domain.ClientDomain;
import com.bank.pan.application.port.outbound.ClientPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class H2ClientPersistence implements ClientPersistencePort {

    private final ClientRepository clientRepository;

    public H2ClientPersistence(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDomain save(ClientDomain clientDomain) {
        var clientSaved = this.clientRepository.save(new ClientEntity(clientDomain.getName(), clientDomain.getCpf()));
        return new ClientDomain(clientSaved.getId(), clientSaved.getName(), clientSaved.getCpf());
    }

    @Override
    public ClientDomain get(String cpf) {
        var clientFound = this.clientRepository.findByCpf(cpf).orElseThrow(()-> new BadRequestException("Client Not Found"));
        return new ClientDomain(clientFound.getId(), clientFound.getName(), clientFound.getCpf());
    }

    @Override
    public List<ClientDomain> getAll() {
        var clientsFound = this.clientRepository.findAll();
        return clientsFound
                .stream()
                .map(client -> new ClientDomain(client.getId(), client.getName(), client.getCpf()))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDomain update(ClientDomain clientDomain) {
        var clientSaved = this.clientRepository.save(new ClientEntity(clientDomain.getId(), clientDomain.getName(), clientDomain.getCpf()));
        return new ClientDomain(clientSaved.getId(), clientSaved.getName(), clientDomain.getCpf());
    }

    @Override
    public void delete(ClientDomain clientDomain) {
        this.clientRepository.delete(new ClientEntity(clientDomain.getId()));
    }

    @Override
    public ClientDomain getById(Integer id) {
        var clientFound =  this.clientRepository.findById(id).orElseThrow(()-> new BadRequestException("Client Not Found"));
        return new ClientDomain(clientFound.getId(), clientFound.getName(), clientFound.getCpf());
    }
}
