package com.csu.edu.controller.v1;

import com.csu.edu.dto.super_game.SuperGameDto;
import com.csu.edu.dto.super_game.SuperGameInfo;
import com.csu.edu.service.SuperGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/super-games")
@RequiredArgsConstructor
public class SuperGameController {
    private final SuperGameService service;

    @GetMapping
    public ResponseEntity<List<SuperGameInfo>> getAllSuperGames(@RequestParam Long categoryId) {
        return ResponseEntity.ok(service.getAllSuperGames(categoryId));
    }

    @PostMapping
    public ResponseEntity<?> addSuperGame(
            @RequestParam Integer categoryId,
            @RequestBody SuperGameDto superGameDto
    ) {
        service.createSuperGame(categoryId, superGameDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSuperGame(
            @PathVariable Long id,
            @RequestParam Integer categoryId,
            @RequestBody SuperGameDto superGameDto
    ) {
        service.updateSuperGame(id, categoryId, superGameDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSuperGame(
            @PathVariable Long id
    ) {
        service.deleteSuperGame(id);
        return ResponseEntity.ok().build();
    }
}
