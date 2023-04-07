package com.bank.pan.adapters.inbound.controller;

import com.bank.pan.adapters.infra.dto.ClientDTO;
import com.bank.pan.application.port.inbound.StateServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/state")
public class StateController {

    private final StateServicePort stateServicePort;

    public StateController(StateServicePort stateServicePort) {
        this.stateServicePort = stateServicePort;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll(){
        var statesFound = this.stateServicePort.getAll();
        return null;
    }
}
