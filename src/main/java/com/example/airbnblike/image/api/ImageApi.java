package com.example.airbnblike.image.api;

import com.example.airbnblike.aws.service.AWSS3Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor @RequestMapping("/api/v1/images")
public class ImageApi {

    private final AWSS3Service s3Service;

    @GetMapping("/{bucket_folder}/{key}")
    public byte[] getImage(@PathVariable("bucket_folder") String bucketFolder, @PathVariable("key") String key) {
        return s3Service.downloadFile(bucketFolder, key);
    }
}
