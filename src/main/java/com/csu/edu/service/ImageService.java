package com.csu.edu.service;

import com.csu.edu.model.Image;
import com.csu.edu.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final S3Service s3Service;
    private final ImageRepository repository;

    @Transactional
    public Image createImage(MultipartFile file) {
        String fileKey = s3Service.uploadImageToS3(file);
        Image image = new Image();
        image.setLink(fileKey);
        return repository.save(image);
    }
}
