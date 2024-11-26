package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.theory.TheoryDto;
import com.csu.edu.dto.theory.TheoryRequestDto;
import com.csu.edu.model.Image;
import com.csu.edu.model.Theme;
import com.csu.edu.model.Theory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface TheoryMapper {

    @Mapping(target = "imageLink", source = "image.link")
    TheoryDto toTheoryDto(Theory theory);

    @Mapping(target = "image", source = "image")
    @Mapping(target = "theme", source = "theme")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "themeId", source = "theme.id")
    Theory fromCreateTheoryDto(TheoryRequestDto dto, Theme theme, Image image);

    @Mapping(target = "image", source = "image")
    @Mapping(target = "theme", source = "theme")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "themeId", source = "theme.id")
    void updateFromTheoryRequestDto(@MappingTarget Theory theory, TheoryRequestDto dto, Theme theme, Image image);
}
