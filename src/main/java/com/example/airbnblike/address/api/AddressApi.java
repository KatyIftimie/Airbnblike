package com.example.airbnblike.address.api;

import com.example.airbnblike.address.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressApi {

    private final AddressService addressService;
}
