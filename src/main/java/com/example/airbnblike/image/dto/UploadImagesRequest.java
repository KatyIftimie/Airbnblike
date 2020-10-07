package com.example.airbnblike.image.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data @AllArgsConstructor @NoArgsConstructor
public class UploadImagesRequest {

    private MultipartFile file1, file2, file3, file4, file5;
}