package com.csu.edu.dto.themes;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UpdateThemeDto(
        @NotNull String name,
        @NotNull String description,
        @NotNull String fileLink,
        @NotNull Integer categoryId,
        @NotNull MultipartFile imageFile
) {
}
