package com.bank.pan.application.port.inbound;

import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.domain.CityDomain;
import com.bank.pan.application.domain.DistrictDomain;

import java.util.List;

public interface AddressServicePort {
    AddressDomain getAddressByZipCode(String zipCode);
    List<DistrictDomain> getAllDistricts();

    List<CityDomain> getCitiesByDistrictId(Integer districtId);

    AddressDomain updateAddressClient(AddressDomain addressDomain);
}
