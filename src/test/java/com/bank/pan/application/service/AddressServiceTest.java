package com.bank.pan.application.service;

import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.domain.CityDomain;
import com.bank.pan.application.domain.ClientDomain;
import com.bank.pan.application.domain.DistrictDomain;
import com.bank.pan.application.port.outbound.AddressClientPort;
import com.bank.pan.application.port.outbound.AddressPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AddressServiceTest {
    @Mock
    private AddressClientPort addressClientPort;
    @Mock
    private AddressPersistencePort addressPersistencePort;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnAddressWhenPassZipCode(){
        var addressMock = new AddressDomain("012346", "Rua" , "Vila", "Cidade", "SP");
        var zipCode = "012346";

        when(this.addressClientPort.getAddressByZipCode(anyString())).thenReturn(addressMock);

        var addressFound = this.addressService.getAddressByZipCode(zipCode);

        assertEquals(addressFound.getZipcode(),addressMock.getZipcode());
        assertEquals(addressFound.getStreet(),addressMock.getStreet());
        assertEquals(addressFound.getNeighborhood(),addressMock.getNeighborhood());
        assertEquals(addressFound.getCity(),addressMock.getCity());
        assertEquals(addressFound.getDistrict(),addressMock.getDistrict());

    }

    @Test
    void shouldReturnDistricts(){
        var districtsMock = Arrays.asList(
                new DistrictDomain(1, "SP" ),
                new DistrictDomain(2, "RJ" )
        );

        when(this.addressClientPort.getAllDistricts()).thenReturn(districtsMock);

        var districtsFound = this.addressService.getAllDistricts();

        assertEquals(2, districtsFound.size());
    }

    @Test
    void shouldReturnCitiesWhenPassDistrictId(){
        var citiesMock = Arrays.asList(
                new CityDomain("Cidade 1", 1),
                new CityDomain("Cidade 2", 2)
        );
        var districtId = 1;

        when(this.addressClientPort.getCitiesByDistrictId(anyInt())).thenReturn(citiesMock);

        var citiesFound = this.addressService.getCitiesByDistrictId(districtId);

        assertEquals(2, citiesFound.size());
    }

    @Test
    void shouldSaveAddressClient(){
        var addressMock = new AddressDomain("012346", "Rua" , "number", "complement", "Vila", "Cidade", "SP", 1);
        var clientMock = new ClientDomain("Nome", "12346578901");

        when(this.clientService.getById(addressMock.getClientId())).thenReturn(clientMock);
        when(this.addressPersistencePort.save(any(AddressDomain.class))).thenReturn(addressMock);

        var addressSaved = this.addressService.updateAddressClient(addressMock);

        assertEquals(addressSaved.getZipcode(), addressMock.getZipcode());

    }
}
