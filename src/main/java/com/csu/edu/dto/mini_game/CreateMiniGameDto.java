package com.csu.edu.dto.mini_game;

import java.util.List;

public record CreateMiniGameDto(
        String name,
        String question,
        String imageLink,
        List<CreateChoiceDto> choices
) {
}
