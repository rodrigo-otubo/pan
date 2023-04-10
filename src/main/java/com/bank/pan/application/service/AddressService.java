package com.bank.pan.application.service;

import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.domain.CityDomain;
import com.bank.pan.application.domain.DistrictDomain;
import com.bank.pan.application.port.inbound.AddressServicePort;
import com.bank.pan.application.port.outbound.AddressClientPort;
import com.bank.pan.application.port.outbound.AddressPersistencePort;

import java.util.List;
import java.util.stream.Collectors;

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
    public AddressDomain getAddressByZipCode(String zipCode) {
        return this.addressClientPort.getAddressByZipCode(zipCode);
    }

    @Override
    public List<DistrictDomain> getAllDistricts() {
        return this.addressClientPort.getAllDistricts();
    }

    @Override
    public List<CityDomain> getCitiesByDistrictId(Integer districtId) {
        return this.addressClientPort.getCitiesByDistrictId(districtId);
    }

    @Override
    public AddressDomain updateAddressClient(AddressDomain addressDomain) {
        this.clientService.getById(addressDomain.getClientId());
        return this.addressPersistencePort.save(addressDomain);
    }
}
