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
import com.csu.edu.model.MiniGameChoice;
import com.csu.edu.repository.MiniGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiniGameService {

    private static final String MINI_GAME_NOT_EXISTS_MESSAGE = "Mini game with id '%s' doesn't exist";

    private final MiniGameRepository repository;
    private final MiniGameMapper mapper;
    private final ChoiceService choiceService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public MiniGameDto getMiniGameById(Long id) {
        return repository.findMiniGameWithImageAndChoicesById(id)
                .map(mapper::toMiniGameDto)
                .orElseThrow(() -> new DataNotFoundException(MINI_GAME_NOT_EXISTS_MESSAGE.formatted(id)));
    }

    @Transactional
    public void createMiniGame(int categoryId, CreateMiniGameDto dto) {
        Category category = categoryService.findByIdOrElseThrow(categoryId);
        Image image = imageService.createImage(dto.imageFile());
        MiniGame miniGame = mapper.fromCreateMiniGameDto(dto, category, image);

        repository.save(miniGame);
    }

    private void validateChoices(List<CreateChoiceDto> choices) {
        if (choices == null || choices.isEmpty()) {
            throw new WrongRequestException("Choice must not be null or empty");
        }
        int correctCount = choices.stream().filter(CreateChoiceDto::isCorrect).toList().size();
        if (correctCount != 1) {
            throw new WrongRequestException("The question must contain one correct answer");
        }
    }
}