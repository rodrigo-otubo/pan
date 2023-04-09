package com.bank.pan.application.port.outbound;

import com.bank.pan.adapters.outbound.client.mapper.StateMapper;

import java.util.List;

public interface AddressClientPort {
    void getAddressByZipCode(String zipCode);
    List<StateMapper> getAllStates();
    void getCitiesByStateId(Integer stateId);
}
