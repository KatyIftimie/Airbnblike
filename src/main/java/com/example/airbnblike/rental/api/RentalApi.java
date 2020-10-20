package com.example.airbnblike.rental.api;

import com.example.airbnblike.image.dto.UploadImagesRequest;
import com.example.airbnblike.rental.dto.RentalDto;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.rental.model.RentalType;
import com.example.airbnblike.rental.service.RentalService;
import com.example.airbnblike.room.model.Room;
import com.example.airbnblike.room.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rentals")
public class RentalApi {

    private final RentalService rentalService;
    private final RoomService roomService;

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

    @PostMapping
    public Map<String, Long> addRental(@RequestBody RentalDto rentalDto) {
        return rentalService.addRental(rentalDto);
    }

    @PostMapping(value = "/{rentalID}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addImages(
            @PathVariable("rentalID") String rentalID, 
            @RequestPart(required = false) MultipartFile file1, 
            @RequestPart(required = false) MultipartFile file2, 
            @RequestPart(required = false) MultipartFile file3,
            @RequestPart(required = false) MultipartFile file4,
            @RequestPart(required = false) MultipartFile file5
    ) {
        UploadImagesRequest uploadImagesRequest = new UploadImagesRequest();
        uploadImagesRequest.setFile1(file1);
        uploadImagesRequest.setFile2(file2);
        uploadImagesRequest.setFile3(file3);
        uploadImagesRequest.setFile4(file4);
        uploadImagesRequest.setFile5(file5);
        rentalService.uploadImagesForRental(Long.valueOf(rentalID), uploadImagesRequest);
    }

    @GetMapping("/count-rentals-and-rooms")
    public Map<String, Integer> countAllRentalsAndRooms() {
        Integer countRentals = rentalService.countAllRentals();
        Integer countRooms = roomService.countAllRooms();
        Map<String, Integer> response = new HashMap<>();
        response.put("count_rentals", countRentals);
        response.put("count_rooms", countRooms);
        return response;
    }
}
