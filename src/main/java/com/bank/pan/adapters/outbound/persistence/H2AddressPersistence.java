package com.bank.pan.adapters.outbound.persistence;

import com.bank.pan.adapters.outbound.persistence.entity.AddressEntity;
import com.bank.pan.adapters.outbound.persistence.entity.ClientEntity;
import com.bank.pan.adapters.outbound.persistence.repository.AddressRepository;
import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.port.outbound.AddressPersistencePort;
import org.springframework.stereotype.Component;

@Component
public class H2AddressPersistence implements AddressPersistencePort {

    private final AddressRepository addressRepository;

    public H2AddressPersistence(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDomain save(AddressDomain address) {
        var addressSaved = this.addressRepository.save(new AddressEntity(address.getZipcode(),
                address.getStreet(), address.getNumber(),
                address.getComplement(), address.getNeighborhood(),
                address.getCity(), address.getDistrict(), new ClientEntity(address.getClientId())));
        return new AddressDomain(addressSaved.getId(), addressSaved.getZipcode(),
                addressSaved.getStreet(), addressSaved.getNumber(), addressSaved.getComplement(),
                addressSaved.getNeighborhood(), addressSaved.getCity(), addressSaved.getDistrict(),
                addressSaved.getClient().getId());
    }
}
