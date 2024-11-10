package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.mini_game.ChoiceDto;
import com.csu.edu.dto.mini_game.CreateChoiceDto;
import com.csu.edu.model.Image;
import com.csu.edu.model.MiniGameChoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ChoiceMapper {

    @Mapping(target = "isCorrect", source = "correct")
    @Mapping(target = "imageLink", source = "image.link")
    ChoiceDto toChoiceDto(MiniGameChoice choice);

    @Mapping(target = "correct", source = "dto.isCorrect")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "id", ignore = true)
    MiniGameChoice fromCreateChoiceDto(CreateChoiceDto dto, Image image);
}
