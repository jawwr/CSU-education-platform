package com.csu.edu.controller.v1;

import com.csu.edu.dto.theory.CreateTheoryDto;
import com.csu.edu.dto.theory.TheoryDto;
import com.csu.edu.service.TheoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

@RestController
@RequestMapping("/api/v1/theories")
@RequiredArgsConstructor
public class TheoryController {
    private final TheoryService service;

    @GetMapping
    public ResponseEntity<List<TheoryDto>> getAllTheoriesByThemeId(@RequestParam Long themeId) {
        return ResponseEntity.ok(service.getAllTheoriesByThemeId(themeId));
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA)
    public ResponseEntity<?> createTheory(@ModelAttribute CreateTheoryDto createTheoryDto) {
        service.createTheory(createTheoryDto);
        return ResponseEntity.ok().build();
    }
}
