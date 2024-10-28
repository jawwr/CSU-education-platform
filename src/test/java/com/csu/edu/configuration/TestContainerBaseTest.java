package com.csu.edu.configuration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@ContextConfiguration(initializers = {PostgresContainerInitializer.class})
public class TestContainerBaseTest extends BaseTests{
    public static final PostgreSQLContainer<?> postgres;

    static {
        postgres = new PostgresContainer(imageNameOf("postgres:17.0"))
                .withDatabaseName("integration-tests")
                .withUsername("edu")
                .withPassword("edu");
    }

    @BeforeAll
    public static void beforeAll() {
        postgres.start();
    }


    private static DockerImageName imageNameOf(String name) {
        String compatible = name.replace("(:[\\d.]+)|(:latest)", "");
        return DockerImageName.parse(name)
                .asCompatibleSubstituteFor(compatible);
    }
}
