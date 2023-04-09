package com.bank.pan.adapters.inbound.controller;

import com.bank.pan.adapters.infra.dto.AddressDTO;
import com.bank.pan.adapters.outbound.client.mapper.AddressMapper;
import com.bank.pan.adapters.outbound.client.mapper.DistrictMapper;
import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.port.inbound.AddressServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/address")
public class AddressController {

    private final AddressServicePort addressServicePort;

    public AddressController(AddressServicePort addressServicePort) {
        this.addressServicePort = addressServicePort;
    }

    @GetMapping("{zipCode}")
    public ResponseEntity<AddressMapper> getAdrressByZipCode(@PathVariable String zipCode) {
        var addressFound = this.addressServicePort.getAddressByZipCode(zipCode);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressFound);
    }

    @GetMapping
    public ResponseEntity<List<DistrictMapper>> getAllDistricts() {
        var districtsFound = this.addressServicePort.getAllDistricts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(districtsFound);
    }

    @GetMapping("{districtId}")
    public ResponseEntity<?> getCitiesByDistrictId(@PathVariable Integer districtId) {
        this.addressServicePort.getCitiesByDistrictId(districtId);
        return null;
    }

    @PutMapping
    public ResponseEntity<AddressDTO> updateAddressClient(@RequestBody AddressDTO addressDTO) {
        var addressSaved = this.addressServicePort.updateAddressClient(
                new AddressDomain(addressDTO.getZipcode(),addressDTO.getStreet(), addressDTO.getNumber(),
                        addressDTO.getComplement(), addressDTO.getNeighborhood(), addressDTO.getCity(),
                        addressDTO.getDistrict(), addressDTO.getClientId()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AddressDTO(addressSaved.getId(), addressSaved.getZipcode(),
                        addressSaved.getStreet(), addressSaved.getNumber(),
                        addressSaved.getComplement(), addressSaved.getNeighborhood(),
                        addressSaved.getCity(), addressSaved.getDistrict(),
                        addressSaved.getClientId()));
    }
}
