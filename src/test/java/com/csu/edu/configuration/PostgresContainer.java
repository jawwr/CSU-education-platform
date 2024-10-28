package com.csu.edu.configuration;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class PostgresContainer extends PostgreSQLContainer<PostgresContainer> {

    public PostgresContainer(DockerImageName dockerImageName) {
        super(dockerImageName);
    }
}
