package com.bank.pan.application.port.inbound;

import com.bank.pan.application.domain.ClientDomain;

import java.util.List;

public interface ClientServicePort {


    ClientDomain save(ClientDomain clientDomain);

    ClientDomain get(String cpf);

    ClientDomain update(ClientDomain clientDomain);

    void delete(String cpf);

    ClientDomain getById(Integer id);
}
