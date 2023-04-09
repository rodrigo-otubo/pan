package com.bank.pan.adapters.outbound.client;

import com.bank.pan.adapters.infra.error.exceptions.FeignClientException;
import com.bank.pan.adapters.outbound.client.feign.IBGEFeignClient;
import com.bank.pan.adapters.outbound.client.feign.ViaCepFeignClient;
import com.bank.pan.adapters.outbound.client.mapper.AddressMapper;
import com.bank.pan.adapters.outbound.client.mapper.DistrictMapper;
import com.bank.pan.adapters.outbound.persistence.repository.AddressRepository;
import com.bank.pan.application.port.outbound.AddressClientPort;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressClient implements AddressClientPort {

    private final AddressRepository addressRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressClient.class);
    private final ViaCepFeignClient viaCepFeignClient;
    private final IBGEFeignClient ibgeFeignClient;

    public AddressClient(AddressRepository addressRepository, ViaCepFeignClient viaCepFeignClient, IBGEFeignClient ibgeFeignClient) {
        this.addressRepository = addressRepository;
        this.viaCepFeignClient = viaCepFeignClient;
        this.ibgeFeignClient = ibgeFeignClient;
    }

    @Override
    public AddressMapper getAddressByZipCode(String zipCode) {
        AddressMapper addressMapper;
        try {
            addressMapper = this.viaCepFeignClient.getAddressByZipCode(zipCode);
        } catch (FeignException e){
            LOGGER.error("Error at ViaCepFeignClient : " + e.getMessage());
            throw new FeignClientException("Error ViaCep Client");
        }
        return addressMapper;
    }

    @Override
    public List<DistrictMapper> getAllDistricts() {
        List<DistrictMapper> districts;
        try {
            districts = this.ibgeFeignClient.getAllDistricts();
        } catch (FeignException e) {
            LOGGER.error("Error at IBGEFeignClient in GET All Districts: " + e.getMessage());
            throw new FeignClientException("Error at IBGE Client in get All Districts");
        }
        return districts;
    }

    @Override
    public void getCitiesByDistrictId(Integer districtId) {
        try {
            this.ibgeFeignClient.getCitiesByDistrictId(districtId);
        } catch (FeignException e){
            LOGGER.error("Error at IBGE Client in GET Cities By District Id: " + e.getMessage());
            throw new FeignClientException("Error at IBGE Client in get District By District Id");
        }
    }

}
