package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.CategoryDto;
import com.csu.edu.dto.CategoryInfo;
import com.csu.edu.dto.CreateCategoryDto;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "imageLink", source = "image.link")
    CategoryInfo toCategoryInfo(Category category);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "imageLink", source = "image.link")
    CategoryDto toCategoryDto(Category category);

    @Mapping(target = "id", ignore = true)
    Category fromCreateCategoryDto(CreateCategoryDto dto, Image image);
}
