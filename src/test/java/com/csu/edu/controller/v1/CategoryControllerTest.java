package com.csu.edu.controller.v1;

import com.csu.edu.configuration.TestContainerBaseTest;
import com.csu.edu.data_creator.CategoryDataCreator;
import com.csu.edu.model.Category;
import com.csu.edu.model.Image;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest extends TestContainerBaseTest {

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
                .andExpectAll(
                        status().is2xxSuccessful(),
                        jsonPath("$").isArray(),
                        jsonPath("$").isNotEmpty(),
                        jsonPath("$.[0].id").isNumber(),
                        jsonPath("$.[0].name").value(categoryName),
                        jsonPath("$.[0].image").value(imageLink)
                );
    }

    @Test
    void getCategoryById() throws Exception {
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

        mockMvc.perform(get("/api/v1/categories/{id}", category.getId()))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        jsonPath("$").isNotEmpty(),
                        jsonPath("$.id").isNumber(),
                        jsonPath("$.name").value(categoryName),
                        jsonPath("$.description").value(description),
                        jsonPath("$.imageLink").value(imageLink)
                );
    }

    @Test
    void getCategoryById_categoryNotExists() throws Exception {
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

        int randomId = RandomUtils.nextInt();

        mockMvc.perform(get("/api/v1/categories/{id}", randomId))
                .andExpectAll(
                        status().isUnprocessableEntity(),
                        jsonPath("$").isNotEmpty(),
                        jsonPath("$.message").isString(),
                        jsonPath("$.message").value("Category with id '" + randomId + "' doesn't exist")
                );
    }
}