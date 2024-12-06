package com.csu.edu.service;

import com.csu.edu.dto.image.ImageDto;
import com.csu.edu.exception.DataNotFoundException;
import com.csu.edu.model.Image;
import com.csu.edu.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @Transactional(readOnly = true)
    public Image getImageByFileKey(String fileKey) {
        return repository.findImageByLink(fileKey)
                .orElseThrow(() -> new DataNotFoundException("Image with fileKey '" + fileKey + "' doesn't exists"));
    }

    @Transactional
    public void deleteImageByFileLink(String fileLink) {
        s3Service.deleteImageByFileLink(fileLink);
        repository.deleteByLink(fileLink);
    }

    @Transactional(readOnly = true)
    public List<ImageDto> getAllImages() {
        return repository.findAll().stream().map(i -> new ImageDto(i.getId(), i.getLink())).toList();
    }
}
