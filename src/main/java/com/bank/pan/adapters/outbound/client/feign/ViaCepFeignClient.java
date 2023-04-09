package com.bank.pan.adapters.outbound.client.feign;

import com.bank.pan.adapters.outbound.client.mapper.AddressMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "locations", url = "https://viacep.com.br/ws")
public interface ViaCepFeignClient {

    @GetMapping("/{cep}/json")
    AddressMapper getAddressByZipCode(@PathVariable("cep") String cep);
}
