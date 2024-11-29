package com.csu.edu.service;

import com.csu.edu.dto.mini_game.CreateChoiceDto;
import com.csu.edu.dto.mini_game.CreateMiniGameDto;
import com.csu.edu.dto.mini_game.MiniGameDto;
import com.csu.edu.exception.DataNotFoundException;
import com.csu.edu.exception.WrongRequestException;
import com.csu.edu.mapper.MiniGameMapper;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import com.csu.edu.model.MiniGame;
import com.csu.edu.repository.MiniGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiniGameService {

    private final MiniGameRepository repository;
    private final MiniGameMapper mapper;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public List<MiniGameDto> getMiniGameByCategoryId(Long categoryId) {
        return repository.findMiniGameWithImageAndChoicesByCategoryId(categoryId)
                .stream()
                .map(mapper::toMiniGameDto)
                .toList();
    }

    @Transactional
    public void createMiniGame(int categoryId, CreateMiniGameDto dto) {
        Category category = categoryService.findByIdOrElseThrow(categoryId);
        Image image = imageService.createImage(dto.imageFile());
        MiniGame miniGame = mapper.fromCreateMiniGameDto(dto, category, image);

        repository.save(miniGame);
    }
}
