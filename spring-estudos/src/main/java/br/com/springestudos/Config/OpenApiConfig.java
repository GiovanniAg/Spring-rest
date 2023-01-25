package br.com.springestudos.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                        .title("Estudos Rest")
                        .version("v1")
                        .description("Api desenvolvida com Java e Spring Boot 3"));
    }
}
