package com.csu.edu.controller.v1;

import com.csu.edu.dto.themes.ThemeInfo;
import com.csu.edu.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/themes")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService service;

    @GetMapping
    public ResponseEntity<List<ThemeInfo>> getAllThemes(@RequestParam Long categoryId) {
        return ResponseEntity.ok(service.getAllThemes(categoryId));
    }
}
