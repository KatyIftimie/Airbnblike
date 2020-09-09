package com.example.airbnblike.address.service;

import com.example.airbnblike.address.dto.AddressDto;
import com.example.airbnblike.address.model.Address;
import com.example.airbnblike.address.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address createModelFromDto(Address address, AddressDto addressDto) {
        address.setAddressLine1(addressDto.getAddressLine1());
        address.setAddressLine2(addressDto.getAddressLine2());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setState(addressDto.getState());
        address.setZipCode(addressDto.getZipCode());
        return address;
    }

    public void save(Address address) {
        addressRepository.save(address);
    }
}
