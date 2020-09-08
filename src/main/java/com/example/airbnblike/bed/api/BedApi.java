package com.example.airbnblike.bed.api;

import com.example.airbnblike.bed.service.BedService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/beds")
public class BedApi {

    private final BedService bedService;
}
