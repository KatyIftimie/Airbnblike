package com.example.airbnblike.bed.api;

import com.example.airbnblike.bed.model.Bed;
import com.example.airbnblike.bed.service.BedService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/api/v1/beds")
public class BedApi {

    private final BedService bedService;
    @Transactional
    @GetMapping
    public List<Bed> getAllBeds(){
        return bedService.getAllBeds();
    }


}
