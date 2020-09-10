package com.example.airbnblike.image.service;

import com.example.airbnblike.aws.service.AWSS3ServiceImpl;
import com.example.airbnblike.image.model.Image;
import com.example.airbnblike.image.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final AWSS3ServiceImpl s3Service;

    public Image getImageByFileName(String fileName) {
        return imageRepository.getByFileName(fileName);
    }

    public void uploadRentalImages(List<Image> rentalImages, List<MultipartFile> imageFiles) {
        rentalImages.forEach(image -> {
            Optional<MultipartFile> fileOptional = getFileFromListByOriginalName(imageFiles, image.getOriginalFileName());
            s3Service.uploadFile(fileOptional.orElse(null), image.getFileName());
        });
    }

    private Optional<MultipartFile> getFileFromListByOriginalName(List<MultipartFile> files, String originalName) {
        return files.stream().filter(file -> Objects.equals(file.getOriginalFilename(), originalName)).findFirst();
    }
}
