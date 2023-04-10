package com.bank.pan.application.service;

import com.bank.pan.adapters.infra.error.exceptions.BadRequestException;
import com.bank.pan.application.domain.ClientDomain;
import com.bank.pan.application.port.outbound.ClientPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientPersistencePort client;

    @InjectMocks
    private ClientService service;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldSaveClient() {
        var clientDomain = new ClientDomain(1,"Rodrigo", "12345678901");

        when(this.client.save(any(ClientDomain.class))).thenReturn(clientDomain);

        var clientSaved = this.service.save(clientDomain);

        assertNotNull(clientSaved.getId());
        assertEquals(clientSaved.getName(), clientDomain.getName());
        assertEquals(clientSaved.getCpf(), clientDomain.getCpf());
    }

    @Test
    void shouldReturnClientByCPF() {
        var clientMock = new ClientDomain(1, "Rodrigo", "12345678901");
        var cpf = "12345678901";

        when(this.client.get(anyString())).thenReturn(clientMock);

        var clientFound = this.service.get(cpf);

        assertNotNull(clientFound.getId());
        assertEquals("Rodrigo", clientFound.getName());
        assertEquals("12345678901", clientFound.getCpf());
    }

    @Test
    void shouldThrowsBadRequestExceptionWhenCPFIsInvalid() {
        var cpf = "0000000000";

        when(this.client.get(anyString())).thenThrow(BadRequestException.class);

        assertThrows(BadRequestException.class, () -> {this.service.get(cpf);});
    }

    @Test
    void shouldReturnClients() {
        var clientsMock = Arrays.asList(
                new ClientDomain(1, "Rodrigo", "12345678901"),
                new ClientDomain(2, "Kaike", "98765432198"));

        when(this.client.getAll()).thenReturn(clientsMock);

        var clientsFound = this.service.getAll();

        assertEquals(2, clientsFound.size());
    }

    @Test
    void shouldUpdateClient() {
        var clientMock = new ClientDomain(1, "Rodrigo Otubo", "12345678901");
        var clientDomain = new ClientDomain("Rodrigo Otubo", "12345678901");

        when(this.client.update(any(ClientDomain.class))).thenReturn(clientMock);

        var clientSaved = this.service.update(clientDomain);

        assertNotNull(clientSaved.getId());
        assertEquals(clientSaved.getName(), clientDomain.getName());
        assertEquals(clientSaved.getCpf(), clientDomain.getCpf());
    }

    @Test
    void shouldDeleteClient() {
        var clientMock = new ClientDomain(1, "Rodrigo Otubo", "12345678901");
        var cpf = "12345679801";

        when(this.client.get(anyString())).thenReturn(clientMock);
        doNothing().when(this.client).delete(any());

        this.service.delete(cpf);

        verify(this.client, times(1)).delete(any());
    }

    @Test
    void shouldThrowsBadRequestExceptionWhenCPFIsInvalidToDelete() {
        var cpf = "0000000000";

        when(this.client.get(anyString())).thenThrow(BadRequestException.class);

        assertThrows(BadRequestException.class, () -> {this.service.delete(cpf);});
    }

    @Test
    void shouldReturnClientById() {
        var clientMock = new ClientDomain(1, "Rodrigo", "12345678901");
        var clientId = 1;

        when(this.client.getById(anyInt())).thenReturn(clientMock);

        var clientFound = this.service.getById(clientId);

        assertNotNull(clientFound.getId());
        assertEquals("Rodrigo", clientFound.getName());
        assertEquals("12345678901", clientFound.getCpf());
    }

    @Test
    void shouldThrowsBadRequestExceptionWhenClientIdIsInvalid() {
        var clientId = 9;

        when(this.client.getById(anyInt())).thenThrow(BadRequestException.class);

        assertThrows(BadRequestException.class, () -> {this.service.getById(clientId);});
    }
}
