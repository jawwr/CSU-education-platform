package com.csu.edu.service;

import com.csu.edu.dto.themes.ThemeInfo;
import com.csu.edu.mapper.ThemeMapper;
import com.csu.edu.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final ThemeRepository repository;
    private final ThemeMapper mapper;

    public List<ThemeInfo> getAllThemes(Long categoryId) {
        return repository.findAllByCategoryIdWithImages(categoryId)
                .stream()
                .map(mapper::toThemeInfo)
                .toList();
    }
}
