package com.example.airbnblike.rental.api;

import com.example.airbnblike.image.dto.UploadImagesRequest;
import com.example.airbnblike.rental.dto.RentalDto;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.rental.model.RentalType;
import com.example.airbnblike.rental.service.RentalService;
import com.example.airbnblike.room.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rentals")
public class RentalApi {

    private final RentalService rentalService;

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

    @PostMapping(value = "/{rentalID}/images", consumes = { "multipart/form-data" })
    public void addImages(@PathVariable("rentalID") String rentalID, @RequestBody UploadImagesRequest uploadImagesRequest, HttpServletResponse response, HttpSession session) throws IOException {
        rentalService.uploadImagesForRental(Long.valueOf(rentalID), uploadImagesRequest);
        response.sendRedirect("/rental-details/" + rentalID);
    }
}
