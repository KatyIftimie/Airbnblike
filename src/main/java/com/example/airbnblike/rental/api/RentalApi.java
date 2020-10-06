package com.example.airbnblike.rental.api;

import com.example.airbnblike.TestImageService;
import com.example.airbnblike.aws.service.AWSS3Service;
import com.example.airbnblike.image.service.ImageService;
import com.example.airbnblike.rental.dto.RentalDto;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.rental.model.RentalType;
import com.example.airbnblike.rental.repository.RentalRepository;
import com.example.airbnblike.rental.service.RentalService;
import com.example.airbnblike.room.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rentals")
public class RentalApi {

    private final RentalService rentalService;
    private final ImageService imageService;
    private final RentalRepository rentalRepository;

    @Transactional
    @GetMapping("/country/{country}")
    public List<Rental> getRentalsByCountry(@PathVariable("country") String country) {
        return rentalService.getRentalsByCountry(country);
    }

    @GetMapping("/{rentalID}")
    public Rental getRentalByID(@PathVariable("rentalID") String rentalID) {
        return rentalService.getRentalByID(Long.valueOf(rentalID));
    }

    @GetMapping("/{rentalID}/rooms")
    public List<Room> getRentalRooms(@PathVariable("rentalID") String rentalID) {
        return rentalService.getRentalRooms(Long.valueOf(rentalID));
    }

    @Transactional
    @GetMapping("/rental-types")
    public List<RentalType> getAllRentalTypes() {
        return rentalService.getAllRentalTypes();
    }

    @PostMapping()
    public ResponseEntity<String> addRental(@RequestBody RentalDto rentalDto) {
        System.out.println("this is it:" + rentalDto);
        Rental newRental = rentalService.addRental(rentalDto);
//        rentalRepository.save(newRental);

//        imageService.uploadRentalImages(newRental.getImages(), rentalDto.getImages());
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
