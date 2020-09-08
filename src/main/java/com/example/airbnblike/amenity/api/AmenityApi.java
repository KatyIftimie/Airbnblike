package com.example.airbnblike.amenity.api;

import com.example.airbnblike.amenity.service.AmenityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/amenities")
public class AmenityApi {

    private final AmenityService amenityService;
}
