package com.csu.edu.service;

import com.csu.edu.dto.CategoryDto;
import com.csu.edu.dto.CategoryInfo;
import com.csu.edu.exception.DataNotFoundException;
import com.csu.edu.mapper.CategoryMapper;
import com.csu.edu.model.Category;
import com.csu.edu.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Transactional(readOnly = true)
    public List<CategoryInfo> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(mapper::toCategoryInfo)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryDto getCategoryById(int id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category with id '" + id + "' not found"));
        return mapper.toCategoryDto(category);
    }
}
