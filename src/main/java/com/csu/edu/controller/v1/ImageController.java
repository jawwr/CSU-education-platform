package com.csu.edu.controller.v1;

import com.csu.edu.dto.image.CreateImageDto;
import com.csu.edu.service.ImageService;
import com.csu.edu.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final S3Service s3Service;
    private final ImageService imageService;

    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok()
                .headers(headers)
                .body(s3Service.downloadImageFromS3(imageName));
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@ModelAttribute CreateImageDto image) {
        return ResponseEntity.ok(imageService.createImage(image.imageFile()).getLink());
    }
}
