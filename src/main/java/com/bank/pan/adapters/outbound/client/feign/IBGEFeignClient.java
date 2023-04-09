package com.bank.pan.adapters.outbound.client.feign;

import com.bank.pan.adapters.outbound.client.mapper.StateMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "address", url = "https://servicodados.ibge.gov.br/api")
public interface IBGEFeignClient {

    @GetMapping("/v1/localidades/estados/")
    List<StateMapper> getAllStates();

    @GetMapping("/v1/localidades/estados/{id}/municipios")
    ResponseEntity<String> getCitiesByStateId(@PathVariable("id") Integer id);
}
