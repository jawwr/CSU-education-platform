package com.csu.edu.configuration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

@Testcontainers
public class TestContainerBaseTest extends BaseTests {
    public static final PostgreSQLContainer<?> POSTGRES_CONTAINER;
    public static final GenericContainer<?> S3_CONTAINER;

    private static final String S3_SECRET_KEY = "edu-app-secret-key";
    private static final String S3_ACCESS_KEY = "edu-app-access-key";

    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:17.0")
                .withDatabaseName("integration-tests")
                .withUsername("edu")
                .withPassword("edu");

        S3_CONTAINER = new GenericContainer<>("minio/minio")
                .withEnv(Map.of("MINIO_ACCESS_KEY", S3_ACCESS_KEY,
                        "MINIO_SECRET_KEY", S3_SECRET_KEY)
                )
                .withExposedPorts(9000)
                .withCommand("server /data");
    }

    @BeforeAll
    public static void beforeAll() {
        POSTGRES_CONTAINER.start();
        S3_CONTAINER.start();
    }

    @DynamicPropertySource
    private static void propertySource(DynamicPropertyRegistry registry) {
        registry.add("S3_SECRET_KEY", () -> S3_SECRET_KEY);
        registry.add("S3_ACCESS_KEY", () -> S3_ACCESS_KEY);
        registry.add("S3_BUCKET_NAME", () -> "integration-tests");
        registry.add("S3_ENDPOINT", () -> S3_CONTAINER.getHost() + ":" + S3_CONTAINER.getMappedPort(9000));
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("DB_USERNAME", POSTGRES_CONTAINER::getUsername);
        registry.add("DB_PASSWORD", POSTGRES_CONTAINER::getPassword);
    }

    private static DockerImageName imageNameOf(String name) {
        String compatible = name.replace("(:[\\d.]+)|(:latest)", "");
        return DockerImageName.parse(name)
                .asCompatibleSubstituteFor(compatible);
    }
}
