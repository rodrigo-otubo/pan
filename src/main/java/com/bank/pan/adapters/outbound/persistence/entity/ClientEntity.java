package com.bank.pan.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "cpf")
    private String cpf;

    public ClientEntity(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public ClientEntity(Integer id) {
        this.id = id;
    }
}
