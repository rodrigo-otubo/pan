package com.bank.pan.adapters.outbound.persistence;

import com.bank.pan.adapters.outbound.persistence.entity.AddressEntity;
import com.bank.pan.adapters.outbound.persistence.repository.AddressRepository;
import com.bank.pan.application.port.outbound.AddressPersistencePort;
import org.springframework.stereotype.Component;

@Component
public class H2AddressPersistence implements AddressPersistencePort {

    private final AddressRepository addressRepository;

    public H2AddressPersistence(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressEntity save(AddressEntity address) {
        return addressRepository.save(address);
    }
}
