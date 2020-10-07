package com.example.airbnblike.aws.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

    void uploadFile(MultipartFile file, String fileName, String bucketFolder);
}

