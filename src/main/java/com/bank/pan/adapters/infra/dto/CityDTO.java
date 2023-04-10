package com.bank.pan.adapters.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private String name;
    private Integer districtId;
}