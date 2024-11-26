package com.csu.edu.dto.theory;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record TheoryRequestDto(
        @NotNull Long themeId,
        @NotNull String text,
        @NotNull Integer pageNumber,
        @NotNull MultipartFile imageFile
) {
}
