package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.themes.CreateThemeDto;
import com.csu.edu.dto.themes.ThemeDto;
import com.csu.edu.dto.themes.ThemeInfo;
import com.csu.edu.dto.themes.UpdateThemeDto;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import com.csu.edu.model.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface ThemeMapper {

    @Mapping(target = "imageLink", source = "image.link")
    ThemeInfo toThemeInfo(Theme theme);

    @Mapping(target = "imageLink", source = "image.link")
    ThemeDto toThemeDto(Theme theme);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    Theme fromCreateThemeDto(CreateThemeDto dto, Category category, Image image);

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    void fromUpdateThemeDto(@MappingTarget Theme theme, UpdateThemeDto dto, Category category, Image image);
}
