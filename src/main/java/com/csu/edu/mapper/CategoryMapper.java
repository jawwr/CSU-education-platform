package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.CategoryInfo;
import com.csu.edu.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryInfo toCategoryInfo(Category category);
}
