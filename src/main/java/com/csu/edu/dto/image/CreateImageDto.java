package com.csu.edu.dto.image;

import org.springframework.web.multipart.MultipartFile;

public record CreateImageDto(
        MultipartFile imageFile
) {
}
