package com.csu.edu.controller.v1;

import com.csu.edu.dto.category.CategoryDto;
import com.csu.edu.dto.category.CategoryInfo;
import com.csu.edu.dto.category.CreateCategoryDto;
import com.csu.edu.dto.category.UpdateCategoryDto;
import com.csu.edu.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryInfo>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA)
    public ResponseEntity<?> createCategory(@ModelAttribute CreateCategoryDto dto) {
        service.createCategory(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MULTIPART_FORM_DATA)
    public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @ModelAttribute UpdateCategoryDto dto) {
        service.updateCategory(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id) {
        service.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }
}
