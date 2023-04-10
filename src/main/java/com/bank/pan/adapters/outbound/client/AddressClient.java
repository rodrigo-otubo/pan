package com.bank.pan.adapters.outbound.client;

import com.bank.pan.adapters.infra.error.exceptions.FeignClientException;
import com.bank.pan.adapters.outbound.client.feign.IBGEFeignClient;
import com.bank.pan.adapters.outbound.client.feign.ViaCepFeignClient;
import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.domain.CityDomain;
import com.bank.pan.application.domain.DistrictDomain;
import com.bank.pan.application.port.outbound.AddressClientPort;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public AddressDomain getAddressByZipCode(String zipCode) {
        AddressDomain address = null;
        try {
            var addressFound = this.viaCepFeignClient.getAddressByZipCode(zipCode);
            if (addressFound.getStatusCode().is2xxSuccessful()){
                address = new AddressDomain(
                        addressFound.getBody().get("cep").asText(),
                        addressFound.getBody().get("logradouro").asText(),
                        addressFound.getBody().get("bairro").asText(),
                        addressFound.getBody().get("localidade").asText(),
                        addressFound.getBody().get("uf").asText());
            }
        } catch (FeignException e){
            LOGGER.error("Error at ViaCepFeignClient : " + e.getMessage());
            throw new FeignClientException("Error ViaCep Client");
        }
        return address;
    }

    @Override
    public List<DistrictDomain> getAllDistricts() {
        List<DistrictDomain> districts = null;
        try {
            var districtsFound = this.ibgeFeignClient.getAllDistricts();
            if (districtsFound.getStatusCode().is2xxSuccessful()){
                districts = districtsFound.getBody().stream().map( district -> new DistrictDomain(district.get("id").asInt(), district.get("nome").asText())).collect(Collectors.toList());
                districts.sort((s1, s2) -> {
                    if (s1.getName().equals("São Paulo")) {
                        return -1;
                    } else if (s2.getName().equals("São Paulo")) {
                        return 1;
                    } else if (s1.getName().equals("Rio de Janeiro")) {
                        return -1;
                    } else if (s2.getName().equals("Rio de Janeiro")) {
                        return 1;
                    } else {
                        return s1.getName().compareTo(s2.getName());
                    }
                });
            }
        } catch (FeignException e) {
            LOGGER.error("Error at IBGEFeignClient in GET All Districts: " + e.getMessage());
            throw new FeignClientException("Error at IBGE Client in get All Districts");
        }
        return districts;
    }

    @Override
    public List<CityDomain> getCitiesByDistrictId(Integer districtId) {
        List<CityDomain> cities = null;
        try {
            var citiesFound = this.ibgeFeignClient.getCitiesByDistrictId(districtId);
            if (citiesFound.getStatusCode().is2xxSuccessful()){
                cities = citiesFound.getBody().stream().map(city -> new CityDomain(city.get("nome").asText(),
                        city.get("microrregiao").get("mesorregiao").get("UF").get("id").asInt())).collect(Collectors.toList());
            }

        } catch (FeignException e){
            LOGGER.error("Error at IBGE Client in GET Cities By District Id: " + e.getMessage());
            throw new FeignClientException("Error at IBGE Client in get District By District Id");
        }
        return cities;
    }

}
