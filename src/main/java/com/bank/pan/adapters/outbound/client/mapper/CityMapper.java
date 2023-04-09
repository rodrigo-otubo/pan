package com.bank.pan.adapters.outbound.client.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class CityMapper {
    @JsonProperty("nome")
    private String name;
    @JsonProperty("id")
    private String districtId;
}
