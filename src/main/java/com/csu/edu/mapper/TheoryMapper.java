package com.csu.edu.mapper;

import com.csu.edu.config.MapperConfig;
import com.csu.edu.dto.theory.TheoryDto;
import com.csu.edu.model.Theory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TheoryMapper {

    @Mapping(target = "imageLink", source = "image.link")
    TheoryDto toTheoryDto(Theory theory);
}
