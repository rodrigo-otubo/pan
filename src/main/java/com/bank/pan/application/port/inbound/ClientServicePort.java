package com.bank.pan.application.port.inbound;

import com.bank.pan.application.domain.ClientDomain;

import java.util.List;

public interface ClientServicePort {


    ClientDomain save(ClientDomain clientDomain);

    ClientDomain get(Integer id);

    List<ClientDomain> getAll();

    ClientDomain update(ClientDomain clientDomain);

    void delete(Integer id);
}
