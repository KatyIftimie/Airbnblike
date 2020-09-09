package com.example.airbnblike.address.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String addressLine1;
    private String addressLine2;
    private String country;
    private String state;
    private String city;
    private String zipCode;
}
