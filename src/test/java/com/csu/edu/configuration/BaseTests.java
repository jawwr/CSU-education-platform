package com.csu.edu.configuration;

import com.csu.edu.EduApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = EduApplication.class)
@AutoConfigureMockMvc
@Sql(value = "/sql/clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BaseTests {

    @Autowired
    protected MockMvc mockMvc;
}
