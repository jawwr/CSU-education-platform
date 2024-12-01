package com.csu.edu.service;

import com.csu.edu.dto.mini_game.CreateChoiceDto;
import com.csu.edu.dto.mini_game.CreateMiniGameDto;
import com.csu.edu.dto.mini_game.MiniGameDto;
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

    private final MiniGameRepository repository;
    private final MiniGameMapper mapper;
    private final CategoryService categoryService;
    private final ImageService imageService;
    private final ChoiceService choiceService;

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
        Image image = imageService.getImageByFileKey(dto.imageLink());
        validateChoice(dto.choices());
        List<MiniGameChoice> choices = choiceService.createChoices(dto.choices());
        MiniGame miniGame = mapper.fromCreateMiniGameDto(dto, category, image, choices);
        choices.forEach(c -> c.setMiniGame(miniGame));

        repository.save(miniGame);
    }

    private void validateChoice(List<CreateChoiceDto> choices) {
        if (choices == null || choices.isEmpty()) {
            throw new WrongRequestException("Mini game must have at least one choice");
        }
        int correctAnswersCount = choices.stream().filter(CreateChoiceDto::isCorrect).toList().size();
        if (correctAnswersCount > 1) {
            throw new WrongRequestException("Mini game must have only one correct choice");
        }
    }
}
