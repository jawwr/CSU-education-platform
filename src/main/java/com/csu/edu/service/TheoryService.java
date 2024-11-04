package com.csu.edu.service;

import com.csu.edu.dto.theory.CreateTheoryDto;
import com.csu.edu.dto.theory.TheoryDto;
import com.csu.edu.mapper.TheoryMapper;
import com.csu.edu.model.Image;
import com.csu.edu.model.Theme;
import com.csu.edu.model.Theory;
import com.csu.edu.repository.TheoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheoryService {
    private final TheoryRepository repository;
    private final TheoryMapper mapper;
    private final ThemeService themeService;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public List<TheoryDto> getAllTheoriesByThemeId(Long themeId) {
        return repository.findAllByThemeIdWithImage(themeId)
                .stream()
                .map(mapper::toTheoryDto)
                .toList();
    }

    @Transactional
    public void createTheory(CreateTheoryDto createTheoryDto) {
        Theme theme = themeService.findThemeByIdOrElseThrow(createTheoryDto.themeId());
        Image image = imageService.createImage(createTheoryDto.imageFile());
        Theory theory = mapper.fromCreateTheoryDto(createTheoryDto, theme, image);
        repository.save(theory);
    }
}
