package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

/**
 * A Anotação (Annotation) @Configuration indica que a classe é uma fonte de
 * configuração e definição de Beans.
 * 
 */
@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {
		Contact contato = new Contact();

		contato.setEmail("marcelo.barboza@generation.org");
		contato.setName("Marcelo Barboza");
		contato.setUrl("https://github.com/Marcelo7211");
		contato.addExtension("Area", "C&I (Especialista tecnico Generation Brasil)");
		contato.addExtension("Telefone", "+55 13 99125-1961");

		return new OpenAPI()
				.info(new Info().title("Blog Mobile")
						.description("API para gerenciar dados do projeto integrador mobile").version("v0.0.1")
						.contact(contato).license(new License().name("Generation.org").url("generationbrazil.org")))
				.externalDocs(new ExternalDocumentation().description("Marcelo Barboza")
						.url("marcelo.barboza@generation.org"));
	}

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	/**
	 * O Método createApiResponse() adiciona uma descrição (Mensagem), em cada
	 * Resposta HTTP.
	 */
	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}

}