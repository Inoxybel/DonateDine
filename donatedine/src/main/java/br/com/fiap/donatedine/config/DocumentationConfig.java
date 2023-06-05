package br.com.fiap.donatedine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DonateDine API")
                        .description("Uma API para o sistema de classificação e gerenciamento de alimentos")
                        .summary("A API do DonateDine serve como um meio de acesso para o software de classificação com inteligência artificial poder persistir no banco de dados seus processamentos, assim como a API serve para dar acesso à aplicação Mobile para recuperar os dados persistidos no banco de dados.")
                        .version("V1")
                        .contact(new Contact()
                                .name("DonateDine Team")
                                .email("rm92900@fiap.com.br")
                        )
                        .license(new License()
                                .name("MIT Open Soucer")
                                .url("http://donatedine.com/licenca")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")));
    }

}