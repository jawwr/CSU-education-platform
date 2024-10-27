package com.csu.edu.dto.category;

import org.springframework.web.multipart.MultipartFile;

public record UpdateCategoryDto(
        String name,
        String description,
        String fileLink,
        MultipartFile imageFile
) {
}
