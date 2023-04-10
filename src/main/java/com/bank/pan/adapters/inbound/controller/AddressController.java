package com.bank.pan.adapters.inbound.controller;

import com.bank.pan.adapters.infra.dto.AddressDTO;
import com.bank.pan.adapters.infra.dto.CityDTO;
import com.bank.pan.adapters.infra.dto.DistrictDTO;
import com.bank.pan.application.domain.AddressDomain;
import com.bank.pan.application.port.inbound.AddressServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/address")
public class AddressController {

    private final AddressServicePort addressServicePort;

    public AddressController(AddressServicePort addressServicePort) {
        this.addressServicePort = addressServicePort;
    }

    @GetMapping("/zipcode/{zipCode}")
    public ResponseEntity<AddressDTO> getAdrressByZipCode(@PathVariable String zipCode) {
        var addressFound = this.addressServicePort.getAddressByZipCode(zipCode);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AddressDTO(
                        addressFound.getZipcode(),
                        addressFound.getStreet(),
                        addressFound.getNeighborhood(),
                        addressFound.getCity(),
                        addressFound.getDistrict()));
    }

    @GetMapping
    public ResponseEntity<List<DistrictDTO>> getAllDistricts() {
        var districtsFound = this.addressServicePort.getAllDistricts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(districtsFound
                        .stream()
                        .map( district -> new DistrictDTO(district.getId(), district.getName())).collect(Collectors.toList()));
    }

    @GetMapping("/district/{districtId}")
    public ResponseEntity<List<CityDTO>> getCitiesByDistrictId(@PathVariable Integer districtId) {
        var citiesFound = this.addressServicePort.getCitiesByDistrictId(districtId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(citiesFound
                        .stream()
                        .map(city -> new CityDTO(city.getName(), city.getDistrictId())).collect(Collectors.toList()));
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
