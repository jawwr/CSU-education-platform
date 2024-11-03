package com.csu.edu.controller.v1;

import com.csu.edu.dto.themes.ThemeDto;
import com.csu.edu.dto.themes.ThemeInfo;
import com.csu.edu.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> getThemeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getThemeById(id));
    }
}