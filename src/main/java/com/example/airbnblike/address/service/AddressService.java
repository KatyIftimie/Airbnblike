package com.example.airbnblike.address.service;

import com.example.airbnblike.address.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
}
