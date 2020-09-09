package com.example.airbnblike.amenity.service;

import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.amenity.repository.AmenityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    @Transactional
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    public Amenity getAmenityByID(Long ID) {
        return amenityRepository.getOne(ID);
    }
}
