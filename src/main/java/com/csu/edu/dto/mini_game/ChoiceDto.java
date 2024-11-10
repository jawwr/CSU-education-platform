package com.csu.edu.dto.mini_game;

public record ChoiceDto(
        Long id,
        String title,
        boolean isCorrect,
        String imageLink
) {
}
