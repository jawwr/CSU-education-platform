package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.themes.ThemeDto;
import com.csu.edu.dto.themes.ThemeInfo;
import com.csu.edu.model.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ThemeMapper {

    @Mapping(target = "imageLink", source = "image.link")
    ThemeInfo toThemeInfo(Theme theme);

    @Mapping(target = "imageLink", source = "image.link")
    ThemeDto toThemeDto(Theme theme);
}
