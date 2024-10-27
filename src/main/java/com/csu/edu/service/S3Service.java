package com.csu.edu.service;

import com.csu.edu.config.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3Client;
    private final S3Properties s3Properties;

    public String uploadImageToS3(MultipartFile file) {
        String fileKey = getFileKey(file.getOriginalFilename());
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Properties.getBucketName())
                .key(fileKey)
                .contentType(file.getContentType())
                .build();
        try {
            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
            return fileKey;
        } catch (Exception e) {
            log.error("Failed to upload file to s3", e);
            throw new RuntimeException(e);
        }
    }

    public byte[] downloadImageFromS3(String key) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Properties.getBucketName())
                .key(key)
                .build();
        try {
            return s3Client.getObject(request).readAllBytes();
        } catch (Exception e) {
            log.error("Failed to load file from s3", e);
            throw new RuntimeException(e);
        }
    }

    public void deleteImageByFileLink(String fileLink) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(s3Properties.getBucketName())
                .key(fileLink)
                .build();

        try {
            s3Client.deleteObject(deleteRequest);
        } catch (Exception e) {
            log.error("Failed to delete file from s3", e);
            throw new RuntimeException(e);
        }
    }

    private String getFileKey(String originalFileName) {
        return originalFileName.contains(".")
                ? originalFileName.split("\\.")[0]
                : originalFileName;
    }
}
