package com.bank.pan.application.service;

import com.bank.pan.adapters.outbound.client.mapper.AddressMapper;
import com.bank.pan.adapters.outbound.client.mapper.DistrictMapper;
import com.bank.pan.adapters.outbound.persistence.entity.AddressEntity;
import com.bank.pan.adapters.outbound.persistence.entity.ClientEntity;
import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.port.inbound.AddressServicePort;
import com.bank.pan.application.port.outbound.AddressClientPort;
import com.bank.pan.application.port.outbound.AddressPersistencePort;

import java.util.List;

public class AddressService implements AddressServicePort {

    private final AddressClientPort addressClientPort;
    private final AddressPersistencePort addressPersistencePort;

    private final ClientService clientService;

    public AddressService(AddressClientPort addressClientPort, AddressPersistencePort addressPersistencePort, ClientService clientService) {
        this.addressClientPort = addressClientPort;
        this.addressPersistencePort = addressPersistencePort;
        this.clientService = clientService;
    }

    @Override
    public AddressMapper getAddressByZipCode(String zipCode) {
        //TODO talvez tenha que criar um AddressDomain
        return this.addressClientPort.getAddressByZipCode(zipCode);
    }

    @Override
    public List<DistrictMapper> getAllDistricts() {
        //TODO colocar sao paulo em primeiro da lista e rio de janeiro o segundo da lista
        // e o restante ordenar em ordem alfabetica
        // talvez criar um districtdomain
        return this.addressClientPort.getAllDistricts();
    }

    @Override
    public void getCitiesByDistrictId(Integer districtId) {
        this.addressClientPort.getCitiesByDistrictId(districtId);
    }

    @Override
    public AddressDomain updateAddressClient(AddressDomain addressDomain) {
        this.clientService.getById(addressDomain.getClientId());
        var addressSaved = this.addressPersistencePort.save(new AddressEntity(addressDomain.getZipcode(),
                addressDomain.getStreet(), addressDomain.getNumber(),
                addressDomain.getComplement(), addressDomain.getNeighborhood(),
                addressDomain.getCity(), addressDomain.getDistrict(), new ClientEntity(addressDomain.getClientId())));
        return new AddressDomain(addressSaved.getId(), addressSaved.getZipcode(),
                addressSaved.getStreet(), addressSaved.getNumber(), addressSaved.getComplement(),
                addressSaved.getNeighborhood(), addressSaved.getCity(), addressSaved.getDistrict(),
                addressSaved.getClient().getId());
    }
}
