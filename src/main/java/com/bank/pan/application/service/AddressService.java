package com.bank.pan.application.service;

import com.bank.pan.adapters.outbound.client.mapper.StateMapper;
import com.bank.pan.application.port.inbound.AddressServicePort;
import com.bank.pan.application.port.outbound.AddressClientPort;

import java.util.List;

public class AddressService implements AddressServicePort {

    private final AddressClientPort addressClientPort;

    public AddressService(AddressClientPort addressClientPort) {
        this.addressClientPort = addressClientPort;
    }

    @Override
    public void getAddressByZipCode(String zipCode) {
        this.addressClientPort.getAddressByZipCode(zipCode);
    }

    @Override
    public List<StateMapper> getAllStates() {
        return this.addressClientPort.getAllStates();
    }

    @Override
    public void getCitiesByStateId(Integer stateId) {
        this.addressClientPort.getCitiesByStateId(stateId);
    }
}
