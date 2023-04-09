package com.bank.pan.application.port.outbound;

import com.bank.pan.adapters.outbound.client.mapper.AddressMapper;
import com.bank.pan.adapters.outbound.client.mapper.DistrictMapper;
import com.bank.pan.adapters.outbound.persistence.entity.AddressEntity;
import com.bank.pan.application.domain.AddressDomain;

import java.util.List;

public interface AddressClientPort {
    AddressMapper getAddressByZipCode(String zipCode);
    List<DistrictMapper> getAllDistricts();
    void getCitiesByDistrictId(Integer districtId);

}
