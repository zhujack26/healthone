package com.secui.healthone.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "bearerAuth";

        Info info = new Info().title("HealthOne Info Service API").version("Ver.1.0.0")
                .description("HealthAdvice(건강조언), HealthInfo(회원 건강 정보), HealthStat(회원 건강 기록) Domain")
//                .termsOfService("http://swagger.io/terms/")\
//                .contact(new Contact().name("HealthOne").url("http://localhost:8083").email("healthOne@healthone.com"))
                .license(new License().name("MIT Licence").url("https://opensource.org/licenses/MIT"));

        return new OpenAPI()
                .servers(Arrays.asList(
                        new Server().url("https://back.apihealthone.com/info")
                ))
                .info(info)
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

    }

}
