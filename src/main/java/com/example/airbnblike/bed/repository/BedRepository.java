package com.example.airbnblike.bed.repository;

import com.example.airbnblike.bed.model.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {
}
