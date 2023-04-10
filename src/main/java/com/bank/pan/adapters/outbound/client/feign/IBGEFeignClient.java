package com.bank.pan.adapters.outbound.client.feign;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "address", url = "https://servicodados.ibge.gov.br/api")
public interface IBGEFeignClient {

    @GetMapping("/v1/localidades/estados/")
    ResponseEntity<List<JsonNode>> getAllDistricts();

    @GetMapping("/v1/localidades/estados/{id}/municipios")
    ResponseEntity<List<JsonNode>> getCitiesByDistrictId(@PathVariable("id") Integer id);
}
