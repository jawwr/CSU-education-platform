package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.category.CategoryDto;
import com.csu.edu.dto.category.CategoryInfo;
import com.csu.edu.dto.category.CreateCategoryDto;
import com.csu.edu.dto.category.UpdateCategoryDto;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", source = "image")
    void updateCategory(@MappingTarget Category category, UpdateCategoryDto dto, Image image);
}
