package com.gustavonascimento.TesteEstagioDevTrainingProgram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI devTrainingProgram() {
		return new OpenAPI().info(new Info().title("API para o teste do Dev Training Program")
				.description("A aplicação dispõe de um endpoint para recuperar todos os usuários")
				.version("v0.0.1")
				.license(new License().name("Apache 2.0").url("https://github.com/Gustavo-Henrique-Lima")));
	}
}