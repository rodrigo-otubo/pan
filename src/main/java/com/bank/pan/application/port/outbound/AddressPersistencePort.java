package com.bank.pan.application.port.outbound;

import com.bank.pan.adapters.outbound.persistence.entity.AddressEntity;

public interface AddressPersistencePort {
    AddressEntity save(AddressEntity address);
}
