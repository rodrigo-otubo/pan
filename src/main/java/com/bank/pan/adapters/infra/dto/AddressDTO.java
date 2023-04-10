package com.bank.pan.adapters.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Integer id;
    private String zipcode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String district;
    private Integer clientId;

    public AddressDTO(String zipcode, String street, String neighborhood, String city, String district) {
        this.zipcode = zipcode;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.district = district;
    }
}
