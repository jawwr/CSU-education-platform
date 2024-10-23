package com.csu.edu.dto;

import org.springframework.web.multipart.MultipartFile;

public record CreateCategoryDto(
        String name,
        String description,
        MultipartFile imageFile
) {
}
