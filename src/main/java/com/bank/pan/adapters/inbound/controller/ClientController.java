package com.bank.pan.adapters.inbound.controller;


import com.bank.pan.adapters.infra.dto.AddressDTO;
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
        var clientSaved = this.clientServicePort.save(
                new ClientDomain(clientDTO.getName(), clientDTO.getCpf()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ClientDTO(clientSaved.getId(), clientSaved.getName(), clientSaved.getCpf()));
    }

    @GetMapping("{cpf}")
    public ResponseEntity<ClientDTO> get(@PathVariable String cpf){
        var clientFound = this.clientServicePort.get(cpf);
        if (clientFound.getAddressDomain() == null){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ClientDTO(clientFound.getId(), clientFound.getName(), clientFound.getCpf()));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ClientDTO(clientFound.getId(), clientFound.getName(), clientFound.getCpf(),
                        new AddressDTO(clientFound.getAddressDomain().getZipcode(),
                                clientFound.getAddressDomain().getStreet(),
                                clientFound.getAddressDomain().getNeighborhood(),
                                clientFound.getAddressDomain().getCity(),
                                clientFound.getAddressDomain().getDistrict())));
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO clientDTO){
        var clientSaved = this.clientServicePort.update(new ClientDomain(clientDTO.getId(), clientDTO.getName(), clientDTO.getCpf()) );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ClientDTO(clientSaved.getId(), clientSaved.getName(), clientSaved.getCpf()));
    }

    @DeleteMapping("{cpf}")
    public ResponseEntity<?> delete(@PathVariable String cpf){
        this.clientServicePort.delete(cpf);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Client deleted successfully");
    }
}
