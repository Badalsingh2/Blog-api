package com.badalcode.blog.blogapi.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;



@Configuration
@OpenAPIDefinition(info = @Info(title = "Blog Application Backend APIs", description = "Project is Developed Prashant Tripathi", version = "v1", license = @License(name = "", url = "https://www.flexidev.co")))
public class SwaggerConfig  {

    public static final String AUTHORIZATION_HEADER = "Authorization";





    // Access local: http://localhost:9090/theta/swagger-ui/index.html
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(AUTHORIZATION_HEADER))
                .components(new Components().addSecuritySchemes(AUTHORIZATION_HEADER,
                        new io.swagger.v3.oas.models.security.SecurityScheme().type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")));
    }

    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

}

