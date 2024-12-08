package com.csu.edu.dto.category;

import com.csu.edu.dto.mini_game.MiniGameInfo;
import com.csu.edu.dto.super_game.SuperGameInfo;

import java.util.List;

public record CategoryDto(
        Long id,
        String description,
        String name,
        String imageLink,
        List<MiniGameInfo> miniGames,
        List<SuperGameInfo> superGames
) {
}
