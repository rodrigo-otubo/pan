package com.bank.pan.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDomain {
    private Integer id;
    private String name;
    private String cpf;

    private AddressDomain addressDomain;

    public ClientDomain(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public ClientDomain(String name) {
        this.name = name;
    }

    public ClientDomain(Integer id) {
        this.id = id;
    }

    public ClientDomain(Integer id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }
}
