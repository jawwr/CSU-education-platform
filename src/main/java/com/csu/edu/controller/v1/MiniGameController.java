package com.csu.edu.controller.v1;

import com.csu.edu.dto.mini_game.CreateMiniGameDto;
import com.csu.edu.dto.mini_game.MiniGameDto;
import com.csu.edu.service.MiniGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mini-games")
@RequiredArgsConstructor
public class MiniGameController {

    private final MiniGameService service;

    @GetMapping
    public ResponseEntity<List<MiniGameDto>> getMiniGame(@RequestParam Long categoryId) {
        return ResponseEntity.ok(service.getMiniGameByCategoryId(categoryId));
    }

    @PostMapping
    public ResponseEntity<?> createMiniGame(@RequestBody CreateMiniGameDto dto,
                                            @RequestParam("categoryId") Integer categoryId) {
        service.createMiniGame(categoryId, dto);
        return ResponseEntity.ok().build();
    }
}
