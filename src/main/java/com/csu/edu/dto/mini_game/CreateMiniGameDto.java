package com.csu.edu.dto.mini_game;

import org.springframework.web.multipart.MultipartFile;

public record CreateMiniGameDto(
        String name,
        String question,
        MultipartFile imageFile
) {
}
