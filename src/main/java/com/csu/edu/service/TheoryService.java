package com.csu.edu.service;

import com.csu.edu.dto.theory.TheoryDto;
import com.csu.edu.mapper.TheoryMapper;
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

    @Transactional(readOnly = true)
    public List<TheoryDto> getAllTheoriesByThemeId(Long themeId) {
        return repository.findAllByThemeIdWithImage(themeId)
                .stream()
                .map(mapper::toTheoryDto)
                .toList();
    }
}
