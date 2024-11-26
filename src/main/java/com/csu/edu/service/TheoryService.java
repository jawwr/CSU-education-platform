package com.csu.edu.service;

import com.csu.edu.dto.theory.TheoryRequestDto;
import com.csu.edu.dto.theory.TheoryDto;
import com.csu.edu.exception.DataNotFoundException;
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
    public void createTheory(TheoryRequestDto theoryRequestDto) {
        Theme theme = themeService.findThemeByIdOrElseThrow(theoryRequestDto.themeId());
        Image image = imageService.createImage(theoryRequestDto.imageFile());
        Theory theory = mapper.fromCreateTheoryDto(theoryRequestDto, theme, image);
        repository.save(theory);
    }

    @Transactional
    public void updateTheory(Long id, TheoryRequestDto theoryDto) {
        Theory theory = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Theory with id " + id + " not found"));
        Theme theme = themeService.findThemeByIdOrElseThrow(theoryDto.themeId());
        Image image = imageService.createImage(theoryDto.imageFile());
        mapper.updateFromTheoryRequestDto(theory, theoryDto, theme, image);
        repository.save(theory);
    }

    @Transactional
    public void deleteTheory(Long id) {
        repository.deleteById(id);
    }
}
