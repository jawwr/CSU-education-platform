package com.csu.edu.dto.category;

public record CategoryDto(
        Long id,
        String description,
        String name,
        String imageLink
) {
}
