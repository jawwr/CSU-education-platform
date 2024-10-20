package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.CategoryInfo;
import com.csu.edu.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryInfo toCategoryInfo(Category category);
}
