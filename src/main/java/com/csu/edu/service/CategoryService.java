package com.csu.edu.service;

import com.csu.edu.dto.CategoryInfo;
import com.csu.edu.mapper.CategoryMapper;
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

    @Transactional(readOnly = true)
    public List<CategoryInfo> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(mapper::toCategoryInfo)
                .toList();
    }
}
