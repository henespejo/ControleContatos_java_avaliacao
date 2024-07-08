package com.projeto.controleContatos.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                ))
                .info(new Info().title("Controle de Contatos")
                        .description("Documentação da API de Controle de Contatos")
                        .version("v1.0.0")
                        .license(new License().name("SpringDoc").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Controle de Contados docs")
                        .url("https://github.com/"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
