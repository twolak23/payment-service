package org.example.payment_service.config;

import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.payment_service.repository.jpa")
@EnableR2dbcRepositories(basePackages = "org.example.payment_service.repository.reactive")
@Import(DataSourceAutoConfiguration.class)
public class RepositoryConfig { }
