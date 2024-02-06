package com.example.sem3HomeTask.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс приложения.
 */
@Configuration
public class ApplicationConfig {

    /**
     * Бин конфигурации документации Swagger.
     * @return подготовленный бин.
     */
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("Обработка пользователей")
                .description("Добавление и сортировка пользователей")
                .version("0.001"));
    }
}
