package com.bank.pan.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "complement")
    private String complement;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    public AddressEntity(String zipcode, String street, String number, String complement, String neighborhood, String city, String district, ClientEntity client) {
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.district = district;
        this.client = client;
    }
}
