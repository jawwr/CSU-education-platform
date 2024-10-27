package com.csu.edu.dto.category;

import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.annotations.NotNull;

public record CreateCategoryDto(
        @NotNull String name,
        @NotNull String description,
        @NotNull MultipartFile imageFile
) {
}
