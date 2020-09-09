package com.example.airbnblike.rental.api;

import com.example.airbnblike.TestImageService;
import com.example.airbnblike.rental.dto.RentalDto;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.rental.model.RentalType;
import com.example.airbnblike.rental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rentals")
public class RentalApi {

    private final RentalService rentalService;
    private final TestImageService testImageService;

    @Transactional
    @GetMapping("/{country}")
    public List<Rental> getRentalsByCountry(@PathVariable("country") String country) {
        return rentalService.getRentalsByCountry(country);
    }

    @Transactional
    @GetMapping("/rental-types")
    public List<RentalType> getAllRentalTypes() {
        return rentalService.getAllRentalTypes();
    }

    @PostMapping()
    public ResponseEntity<String> addRental(@RequestBody RentalDto rentalDto) {
//        try {
//            testImageService.saveImage(rentalDto.getImage());
//        } catch (IOException e) {
//            return new ResponseEntity<>("IMAGE NOT SAVED", HttpStatus.BAD_REQUEST);
//        }
        rentalService.addRental(rentalDto);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
