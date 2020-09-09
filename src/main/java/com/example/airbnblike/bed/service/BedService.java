package com.example.airbnblike.bed.service;

import com.example.airbnblike.bed.model.Bed;
import com.example.airbnblike.bed.repository.BedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BedService {

    private final BedRepository bedRepository;
    @Transactional
    public List<Bed> getAllBeds(){
        return bedRepository.findAll();
    }
}
