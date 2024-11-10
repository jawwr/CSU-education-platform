package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.mini_game.CreateMiniGameDto;
import com.csu.edu.dto.mini_game.MiniGameDto;
import com.csu.edu.dto.mini_game.MiniGameInfo;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import com.csu.edu.model.MiniGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = ChoiceMapper.class)
public interface MiniGameMapper {

    @Mapping(target = "imageLink", source = "image.link")
    MiniGameInfo toMiniGameInfo(MiniGame miniGame);

    MiniGameDto toMiniGameDto(MiniGame miniGame);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "category", source = "category")
    MiniGame fromCreateMiniGameDto(CreateMiniGameDto dto, Category category, Image image);
}
