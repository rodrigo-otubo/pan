package com.bank.pan.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDomain {
    private Integer id;
    private String zipcode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String district;
    private Integer clientId;

    public AddressDomain(String zipcode, String street, String number, String complement, String neighborhood, String city, String district, Integer clientId) {
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.district = district;
        this.clientId = clientId;
    }

    public AddressDomain(String zipcode, String street, String neighborhood, String city, String district) {
        this.zipcode = zipcode;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.district = district;
    }
}
