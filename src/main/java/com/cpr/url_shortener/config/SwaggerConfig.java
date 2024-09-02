package com.cpr.url_shortener.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Url shortener",
                description = "This is a service for shorting your URL\\`s. " +
                        "The service make an alias for URL you working with." +
                        "After creating a URL you should paste the alias after '/shortener/v1/'." +
                        "To create a new URL you must add a url and it\\`s alias in request body." +
                        "To get a URL with an alias ",
                version = "1.0.0"
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Url Shortener")
                        .version("v1.0.0"))
                .components(new Components());
    }

}
