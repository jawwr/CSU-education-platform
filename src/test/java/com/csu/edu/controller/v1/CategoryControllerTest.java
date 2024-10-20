package com.csu.edu.controller.v1;

import com.csu.edu.configuration.BaseTests;
import com.csu.edu.data_creator.CategoryDataCreator;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest extends BaseTests {

    @Autowired
    private CategoryDataCreator dataCreator;

    @Test
    void getAllCategories() throws Exception {
        String description = RandomStringUtils.randomAlphabetic(10);
        String categoryName = RandomStringUtils.randomAlphabetic(10);
        String imageLink = RandomStringUtils.randomAlphabetic(10);

        Image image = new Image();
        image.setLink(imageLink);

        Category category = new Category();
        category.setDescription(description);
        category.setName(categoryName);
        category.setImage(image);

        dataCreator.createCategory(category);

        mockMvc.perform(get("/api/v1/categories"))
                .andDo(print())
                .andExpectAll(
                        status().is2xxSuccessful(),
                        jsonPath("$").isArray(),
                        jsonPath("$").isNotEmpty(),
                        jsonPath("$.[0].id").isNumber(),
                        jsonPath("$.[0].name").value(categoryName),
                        jsonPath("$.[0].description").value(description)
                );
    }
}