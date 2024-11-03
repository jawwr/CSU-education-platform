package com.csu.edu.dto.themes;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CreateThemeDto(
        @NotNull String name,
        @NotNull String description,
        @NotNull int categoryId,
        @NotNull MultipartFile imageFile
) {
}
