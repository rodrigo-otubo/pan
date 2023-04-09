package com.bank.pan.adapters.outbound.client;

import com.bank.pan.adapters.infra.error.exceptions.FeignClientException;
import com.bank.pan.adapters.outbound.client.feign.IBGEFeignClient;
import com.bank.pan.adapters.outbound.client.feign.ViaCepFeignClient;
import com.bank.pan.adapters.outbound.client.mapper.StateMapper;
import com.bank.pan.application.port.outbound.AddressClientPort;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressClient implements AddressClientPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressClient.class);
    private final ViaCepFeignClient viaCepFeignClient;
    private final IBGEFeignClient ibgeFeignClient;

    public AddressClient(ViaCepFeignClient viaCepFeignClient, IBGEFeignClient ibgeFeignClient) {
        this.viaCepFeignClient = viaCepFeignClient;
        this.ibgeFeignClient = ibgeFeignClient;
    }

    @Override
    public void getAddressByZipCode(String zipCode) {
        try {
            this.viaCepFeignClient.getAddressByZipCode(zipCode);
        } catch (FeignException e){
            LOGGER.error("Error at ViaCepFeignClient : " + e.getMessage());
            throw new FeignClientException("Error ViaCep Client");
        }
    }

    @Override
    public List<StateMapper> getAllStates() {
        List<StateMapper> states;
        try {
            states = this.ibgeFeignClient.getAllStates();
        } catch (FeignException e) {
            LOGGER.error("Error at IBGEFeignClient in GET All States: " + e.getMessage());
            throw new FeignClientException("Error at IBGE Client in get All States");
        }
        return states;
    }

    @Override
    public void getCitiesByStateId(Integer stateId) {
        try {
            this.ibgeFeignClient.getCitiesByStateId(stateId);
        } catch (FeignException e){
            LOGGER.error("Error at IBGE Client in GET Cities By State Id: " + e.getMessage());
            throw new FeignClientException("Error at IBGE Client in get Cities By State Id");
        }
    }
}
