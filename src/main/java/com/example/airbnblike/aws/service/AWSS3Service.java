package com.example.airbnblike.aws.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service @AllArgsConstructor
public class AWSS3Service {

    private final static Logger LOGGER = LoggerFactory.getLogger(AWSS3Service.class);
    private final AmazonS3 amazonS3;
    private final String bucketName = "airbnblike";

    public void uploadFile(final MultipartFile multipartFile, String fileName, String bucketFolder) {
        LOGGER.info("File upload in progress...");
        try {
            final File file = convertMultipartFileToFile(multipartFile);
            uploadFileToS3Bucket(file, fileName, bucketFolder);
            LOGGER.info("File {} upload completed", fileName);
            final boolean deleted = file.delete();
            LOGGER.info("File {} deleted: {}", fileName, deleted);
        } catch (final AmazonServiceException | IOException ex) {
            LOGGER.info("File {} upload failed", fileName);
            LOGGER.error("Error = {} while uploading file {}", ex.getMessage(), fileName);
        }
    }

    private File convertMultipartFileToFile(final MultipartFile multipartFile) throws IOException {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting multipart file to file: {}", ex.getMessage());
            throw new IOException();
        }
        return file;
    }

    private void uploadFileToS3Bucket(final File file, final String fileName, final String bucketFolder) {
        LOGGER.info("Uploading file {} to S3 bucket {}", fileName, bucketName);
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName + "/" + bucketFolder, fileName, file);
        amazonS3.putObject(putObjectRequest);
    }

    public byte[] downloadFile(final String bucketFolder, final String key) {
        try {
            final GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName + "/" + bucketFolder, key);
            S3Object object = amazonS3.getObject(getObjectRequest);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            LOGGER.error("Failed to download object with key {}: {}", key, e.getMessage());
            return new byte[0];
        }
    }
}
