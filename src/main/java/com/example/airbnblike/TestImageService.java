package com.example.airbnblike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class TestImageService {

    public void saveImage(MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            byte[] bytes = image.getBytes();
            Path path = Paths.get("D:\\Codecool\\airbnblike\\src\\main\\resources\\images" + image.getOriginalFilename());
            Files.write(path, bytes);
        }
    }
}
