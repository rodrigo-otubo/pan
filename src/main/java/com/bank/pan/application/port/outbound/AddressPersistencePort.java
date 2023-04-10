package com.bank.pan.application.port.outbound;

import com.bank.pan.application.domain.AddressDomain;

public interface AddressPersistencePort {
    AddressDomain save(AddressDomain address);
}
