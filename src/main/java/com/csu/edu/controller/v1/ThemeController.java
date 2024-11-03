package com.csu.edu.controller.v1;

import com.csu.edu.dto.themes.CreateThemeDto;
import com.csu.edu.dto.themes.ThemeDto;
import com.csu.edu.dto.themes.ThemeInfo;
import com.csu.edu.dto.themes.UpdateThemeDto;
import com.csu.edu.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

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

    @PostMapping(consumes = MULTIPART_FORM_DATA)
    public ResponseEntity<?> createTheme(@ModelAttribute CreateThemeDto createThemeDto) {
        service.createTheme(createThemeDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}", consumes = MULTIPART_FORM_DATA)
    public ResponseEntity<?> updateTheme(@PathVariable("id") Long id, @ModelAttribute UpdateThemeDto updateThemeDto) {
        service.updateTheme(id, updateThemeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheme(@PathVariable("id") Long id) {
        service.deleteThemeById(id);
        return ResponseEntity.ok().build();
    }
}
