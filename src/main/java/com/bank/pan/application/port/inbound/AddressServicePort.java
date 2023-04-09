package com.bank.pan.application.port.inbound;

import com.bank.pan.adapters.outbound.client.mapper.AddressMapper;
import com.bank.pan.adapters.outbound.client.mapper.DistrictMapper;
import com.bank.pan.application.domain.AddressDomain;

import java.util.List;

public interface AddressServicePort {
    AddressMapper getAddressByZipCode(String zipCode);
    List<DistrictMapper> getAllDistricts();

    void getCitiesByDistrictId(Integer districtId);

    AddressDomain updateAddressClient(AddressDomain addressDomain);
}
