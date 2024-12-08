package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.super_game.SuperGameDto;
import com.csu.edu.dto.super_game.SuperGameInfo;
import com.csu.edu.model.Category;
import com.csu.edu.model.SuperGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface SuperGameMapper {
    SuperGameInfo toSuperGameInfo(SuperGame superGame);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    SuperGame fromSuperGameDto(SuperGameDto superGameDto, Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category")
    void updateFromSuperGameDto(@MappingTarget SuperGame superGame, SuperGameDto superGameDto, Category category);
}
