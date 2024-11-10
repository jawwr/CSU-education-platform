package com.csu.edu.dto.category;

import com.csu.edu.dto.mini_game.MiniGameInfo;

import java.util.List;

public record CategoryDto(
        Long id,
        String description,
        String name,
        String imageLink,
        List<MiniGameInfo> miniGames
) {
}
