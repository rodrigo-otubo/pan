package com.bank.pan.application.port.inbound;

import com.bank.pan.adapters.outbound.client.mapper.StateMapper;

import java.util.List;

public interface AddressServicePort {
    void getAddressByZipCode(String zipCode);
    List<StateMapper> getAllStates();

    void getCitiesByStateId(Integer stateId);
}
