package com.csu.edu.service;

import com.csu.edu.dto.category.CategoryDto;
import com.csu.edu.dto.category.CategoryInfo;
import com.csu.edu.dto.category.CreateCategoryDto;
import com.csu.edu.dto.category.UpdateCategoryDto;
import com.csu.edu.exception.DataNotFoundException;
import com.csu.edu.exception.WrongRequestException;
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

    private static final String CATEGORY_DOES_NOT_EXIST_MESSAGE = "Category with id '%s' doesn't exist";

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
                .orElseThrow(() -> new DataNotFoundException(CATEGORY_DOES_NOT_EXIST_MESSAGE.formatted(id)));
    }

    @Transactional
    public void createCategory(CreateCategoryDto dto) {
        Image image = imageService.createImage(dto.imageFile());
        Category category = mapper.fromCreateCategoryDto(dto, image);
        repository.save(category);
    }

    @Transactional
    public void updateCategory(int id, UpdateCategoryDto dto) {
        Category savedCategory = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CATEGORY_DOES_NOT_EXIST_MESSAGE.formatted(id)));

        if (dto.imageFile() == null && dto.fileLink() == null) {
            throw new WrongRequestException("Category must have image file or exists file link");
        }
        Image image = dto.imageFile() != null
                ? imageService.createImage(dto.imageFile())
                : imageService.getImageByFileKey(dto.fileLink());

        mapper.updateCategory(savedCategory, dto, image);
        repository.save(savedCategory);
    }
}
