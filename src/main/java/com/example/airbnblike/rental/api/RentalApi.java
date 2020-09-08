package com.example.airbnblike.rental.api;

import com.example.airbnblike.rental.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rental")
public class RentalApi {

private final RentalService rentalService;

//    @PostMapping
//    public ResponseEntity<String> addRental(@RequestPart(value= "file") final MultipartFile multipartFile) {
//        // rentalService.uploadFile(multipartFile);
//    }
}
