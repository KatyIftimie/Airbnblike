package com.example.airbnblike.bed.service;

import com.example.airbnblike.bed.repository.BedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BedService {

    private final BedRepository bedRepository;
}
