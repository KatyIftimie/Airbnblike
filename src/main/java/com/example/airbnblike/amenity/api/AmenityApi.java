package com.example.airbnblike.amenity.api;

import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.amenity.service.AmenityService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/amenities")
@CrossOrigin(origins = "http://localhost:3000")
public class AmenityApi {

    private final AmenityService amenityService;

    @Transactional
    @GetMapping
    public List<Amenity> getAllAmenities() {
        return amenityService.getAllAmenities();
    }
}
