package com.csu.edu.service;

import com.csu.edu.dto.mini_game.CreateChoiceDto;
import com.csu.edu.mapper.ChoiceMapper;
import com.csu.edu.model.Image;
import com.csu.edu.model.MiniGameChoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceService {

    private final ChoiceMapper mapper;
    private final ImageService imageService;

    @Transactional
    public List<MiniGameChoice> createChoices(List<CreateChoiceDto> dtos) {
        return dtos.stream()
                .map(choice -> {
                    Image image = imageService.createImage(choice.imageFile());
                    return mapper.fromCreateChoiceDto(choice, image);
                })
                .toList();
    }
}
