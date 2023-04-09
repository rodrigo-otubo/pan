package com.bank.pan.application.domain;

public enum BrazilStates {

    SP("São Paulo"),
    RJ("Rio de Janeiro"),
    AC("Acre"),
    AL("Alagoas"),
    AP("Amapá"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceará"),
    DF("Distrito Federal"),
    ES("Espírito Santo"),
    GO("Goiás"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Pará"),
    PB("Paraíba"),
    PR("Paraná"),
    PE("Pernambuco"),
    PI("Piauí"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondônia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SE("Sergipe"),
    TO("Tocantins");

    private final String nome;

    BrazilStates(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
