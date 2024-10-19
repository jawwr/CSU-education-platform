package com.csu.edu.controller.v1;

import com.csu.edu.dto.CategoryInfo;
import com.csu.edu.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryInfo>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }
}
