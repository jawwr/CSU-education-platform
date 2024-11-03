package com.csu.edu.service;

import com.csu.edu.dto.themes.CreateThemeDto;
import com.csu.edu.dto.themes.ThemeDto;
import com.csu.edu.dto.themes.ThemeInfo;
import com.csu.edu.exception.DataNotFoundException;
import com.csu.edu.mapper.ThemeMapper;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import com.csu.edu.model.Theme;
import com.csu.edu.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private static final String THEME_DOES_NOT_EXIST_MESSAGE = "Theme with id %s doesn't exist";

    private final ThemeRepository repository;
    private final ThemeMapper mapper;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public List<ThemeInfo> getAllThemes(Long categoryId) {
        return repository.findAllByCategoryIdWithImages(categoryId)
                .stream()
                .map(mapper::toThemeInfo)
                .toList();
    }

    @Transactional(readOnly = true)
    public ThemeDto getThemeById(Long id) {
        return repository.findByIdWithImage(id)
                .map(mapper::toThemeDto)
                .orElseThrow(() -> new DataNotFoundException(THEME_DOES_NOT_EXIST_MESSAGE.formatted(id)));
    }

    @Transactional
    public void createTheme(CreateThemeDto createDto) {
        Category category = categoryService.findByIdOrElseThrow(createDto.categoryId());
        Image image = imageService.createImage(createDto.imageFile());
        Theme theme = mapper.fromCreateThemeDto(createDto, category, image);
        repository.save(theme);
    }
}
