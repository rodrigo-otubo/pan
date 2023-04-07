package com.bank.pan.adapters.inbound.controller;


import com.bank.pan.adapters.infra.dto.ClientDTO;
import com.bank.pan.application.domain.ClientDomain;
import com.bank.pan.application.port.inbound.ClientServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/client")
public class ClientController {

    private final ClientServicePort clientServicePort;

    public ClientController(ClientServicePort clientServicePort) {
        this.clientServicePort = clientServicePort;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO){
        var clientSaved = this.clientServicePort.save(new ClientDomain(clientDTO.getName(), clientDTO.getCpf()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ClientDTO(clientSaved.getId(), clientSaved.getName(), clientSaved.getCpf()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDTO> get(@PathVariable Integer id){
        var clientFound = this.clientServicePort.get(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ClientDTO(clientFound.getId(), clientFound.getName(), clientFound.getCpf()));
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll(){
        var clientsFound = this.clientServicePort.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientsFound
                        .stream()
                        .map(company -> new ClientDTO(
                                company.getId(),
                                company.getName(),
                                company.getCpf()))
                        .collect(Collectors.toList()));
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO){
        var clientSaved = this.clientServicePort.update(new ClientDomain(clientDTO.getName()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ClientDTO(clientSaved.getId(), clientSaved.getName(), clientSaved.getCpf()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        this.clientServicePort.delete(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Client deleted successfully");
    }
}
