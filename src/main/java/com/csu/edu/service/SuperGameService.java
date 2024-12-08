package com.csu.edu.service;

import com.csu.edu.dto.super_game.SuperGameDto;
import com.csu.edu.dto.super_game.SuperGameInfo;
import com.csu.edu.exception.DataNotFoundException;
import com.csu.edu.mapper.SuperGameMapper;
import com.csu.edu.model.Category;
import com.csu.edu.model.SuperGame;
import com.csu.edu.repository.SuperGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperGameService {

    private final SuperGameRepository repository;
    private final CategoryService categoryService;
    private final SuperGameMapper mapper;

    @Transactional(readOnly = true)
    public List<SuperGameInfo> getAllSuperGames(Long categoryId) {
        return repository.findAllByCategoryId(categoryId)
                .stream()
                .map(mapper::toSuperGameInfo)
                .toList();
    }

    @Transactional
    public void createSuperGame(Integer categoryId, SuperGameDto superGameDto) {
        Category category = categoryService.findByIdOrElseThrow(categoryId);
        SuperGame superGame = mapper.fromSuperGameDto(superGameDto, category);
        repository.save(superGame);
    }

    @Transactional
    public void updateSuperGame(Long id, Integer categoryId, SuperGameDto superGameDto) {
        SuperGame superGame = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Super game with id " + id + " not found"));
        Category category = categoryService.findByIdOrElseThrow(categoryId);
        mapper.updateFromSuperGameDto(superGame, superGameDto, category);
        repository.save(superGame);
    }

    @Transactional
    public void deleteSuperGame(Long id) {
        repository.deleteById(id);
    }
}
