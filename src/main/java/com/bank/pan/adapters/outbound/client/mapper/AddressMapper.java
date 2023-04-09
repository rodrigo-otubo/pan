package com.bank.pan.adapters.outbound.client.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class AddressMapper {

    @JsonProperty("cep")
    private String zipcode;
    @JsonProperty("logradouro")
    private String street;
    @JsonProperty("bairro")
    private String neighborhood;
    @JsonProperty("uf")
    private String district;

}
