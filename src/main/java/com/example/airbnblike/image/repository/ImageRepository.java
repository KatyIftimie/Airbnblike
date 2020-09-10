package com.example.airbnblike.image.repository;

import com.example.airbnblike.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image getByFileName(String fileName);
}
