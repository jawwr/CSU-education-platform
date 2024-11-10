package com.csu.edu.dto.mini_game;

import java.util.List;

public record MiniGameDto(
        Long id,
        String name,
        String question,
        List<ChoiceDto> choices
) {

}
