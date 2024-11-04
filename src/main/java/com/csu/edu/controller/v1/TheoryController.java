package com.csu.edu.controller.v1;

import com.csu.edu.dto.theory.TheoryDto;
import com.csu.edu.service.TheoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theories")
@RequiredArgsConstructor
public class TheoryController {
    private final TheoryService service;

    @GetMapping
    public ResponseEntity<List<TheoryDto>> getAllTheoriesByThemeId(@RequestParam Long themeId) {
        return ResponseEntity.ok(service.getAllTheoriesByThemeId(themeId));
    }
}
