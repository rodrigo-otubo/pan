package com.bank.pan.application.port.outbound;

import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.domain.CityDomain;
import com.bank.pan.application.domain.DistrictDomain;

import java.util.List;

public interface AddressClientPort {
    AddressDomain getAddressByZipCode(String zipCode);
    List<DistrictDomain> getAllDistricts();
    List<CityDomain> getCitiesByDistrictId(Integer districtId);

}
