package com.example.airbnblike.amenity.service;

import com.example.airbnblike.amenity.repository.AmenityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;
}
