package com.bank.pan.adapters.inbound.controller;

import com.bank.pan.adapters.outbound.client.mapper.StateMapper;
import com.bank.pan.application.port.inbound.AddressServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/address")
public class AddressController {

    private final AddressServicePort addressServicePort;

    public AddressController(AddressServicePort addressServicePort) {
        this.addressServicePort = addressServicePort;
    }

    @GetMapping("{zipCode}")
    public ResponseEntity<?> getAdrressByZipCode(@PathVariable String zipCode) {
        this.addressServicePort.getAddressByZipCode(zipCode);
        return null;
    }

    @GetMapping
    public ResponseEntity<List<StateMapper>> getAllStates() {
        var statesFound = this.addressServicePort.getAllStates();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(statesFound);
    }

    @GetMapping("{stateId}")
    public ResponseEntity<?> getCitiesByStateId(@PathVariable Integer stateId) {
        this.addressServicePort.getCitiesByStateId(stateId);
        return null;
    }
}
