package com.example.UserTask.config;

import java.util.List;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(info = @Info(
        description = "OpenApi documentation for Customer service",
        title = "Application Management Service",
        version = "1.0"),
        servers = {
                @Server(description = "LOCAL Environment", url = "http://localhost:8080")})
@SecurityScheme(name = "bearerAuth", description = "JWT auth description", scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class OpenAPIConfig {

    // termsOfService = "https://www.currenciesdirect.com/en/info/terms-of-use"
}


