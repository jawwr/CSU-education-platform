package com.csu.edu.dto.mini_game;

import org.springframework.web.multipart.MultipartFile;

public record CreateChoiceDto(
        String title,
        boolean isCorrect,
        MultipartFile imageFile
) {
}
