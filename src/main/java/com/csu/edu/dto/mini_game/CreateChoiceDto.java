package com.csu.edu.dto.mini_game;

public record CreateChoiceDto(
        String title,
        boolean isCorrect,
        String imageLink
) {
}
