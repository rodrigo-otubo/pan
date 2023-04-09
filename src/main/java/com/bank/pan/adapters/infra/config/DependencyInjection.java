package com.bank.pan.adapters.infra.config;

import com.bank.pan.adapters.outbound.client.AddressClient;
import com.bank.pan.adapters.outbound.persistence.H2ClientPersistence;
import com.bank.pan.application.port.outbound.AddressPersistencePort;
import com.bank.pan.application.service.AddressService;
import com.bank.pan.application.service.ClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DependencyInjection {

    @Bean
    public ClientService clientService(H2ClientPersistence h2ClientPersistence) {
        return new ClientService(h2ClientPersistence);
    }

    @Bean
    public AddressService addressService(AddressClient addressClient, AddressPersistencePort addressPersistencePort, ClientService clientService) {
        return new AddressService(addressClient, addressPersistencePort, clientService);
    }
}
