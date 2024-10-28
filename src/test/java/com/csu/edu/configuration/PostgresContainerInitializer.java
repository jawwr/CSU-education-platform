package com.csu.edu.configuration;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import static com.csu.edu.configuration.TestContainerBaseTest.postgres;

@Slf4j
public class PostgresContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        log.info("Initialize postgres");
        TestPropertyValues values = TestPropertyValues.of(
                "spring.datasource.url=" + postgres.getJdbcUrl(),
                "spring.datasource.username=" + postgres.getUsername(),
                "spring.datasource.password=" + postgres.getPassword(),
                "spring.liquibase.url=" + postgres.getJdbcUrl(),
                "spring.liquibase.user=" + postgres.getUsername(),
                "spring.liquibase.password=" + postgres.getPassword(),
                "spring.liquibase.enabled=true"
        );
        values.applyTo(applicationContext);
    }
}
