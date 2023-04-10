package com.bank.pan.adapters.outbound.client.feign;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "locations", url = "https://viacep.com.br/ws")
public interface ViaCepFeignClient {

    @GetMapping("/{cep}/json")
    ResponseEntity<JsonNode> getAddressByZipCode(@PathVariable("cep") String cep);
}
