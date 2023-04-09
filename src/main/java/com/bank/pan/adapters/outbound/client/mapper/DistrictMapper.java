package com.bank.pan.adapters.outbound.client.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class DistrictMapper {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nome")
    private String name;
}
