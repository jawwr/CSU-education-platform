package com.csu.edu.data_creator;

import com.csu.edu.model.Category;
import com.csu.edu.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CategoryDataCreator {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
}
