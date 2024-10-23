package com.csu.edu.service;

import com.csu.edu.dto.CategoryDto;
import com.csu.edu.dto.CategoryInfo;
import com.csu.edu.dto.CreateCategoryDto;
import com.csu.edu.exception.DataNotFoundException;
import com.csu.edu.mapper.CategoryMapper;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import com.csu.edu.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public List<CategoryInfo> getAllCategories() {
        return repository.findAllWithImage()
                .stream()
                .map(mapper::toCategoryInfo)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(int id) {
        return repository.findCategoryWithImageById(id)
                .map(mapper::toCategoryDto)
                .orElseThrow(() -> new DataNotFoundException("Category with id '" + id + "' doesn't exist"));
    }

    @Transactional
    public void createCategory(CreateCategoryDto dto) {
        Image image = imageService.createImage(dto.imageFile());
        Category category = mapper.fromCreateCategoryDto(dto, image);
        repository.save(category);
    }
}
