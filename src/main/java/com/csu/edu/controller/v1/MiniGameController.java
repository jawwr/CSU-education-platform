package com.csu.edu.controller.v1;

import com.csu.edu.dto.mini_game.CreateMiniGameDto;
import com.csu.edu.dto.mini_game.MiniGameDto;
import com.csu.edu.service.MiniGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/mini-games")
@RequiredArgsConstructor
public class MiniGameController {

    private final MiniGameService service;

    @GetMapping("/{id}")
    public ResponseEntity<MiniGameDto> getMiniGame(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getMiniGameById(id));
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMiniGame(@ModelAttribute("miniGame") CreateMiniGameDto dto,
                                            @RequestParam("categoryId") Integer categoryId) {
        service.createMiniGame(categoryId, dto);
        return ResponseEntity.ok().build();
    }
}
