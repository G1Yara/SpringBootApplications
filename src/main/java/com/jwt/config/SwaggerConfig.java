package com.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket swaggerConfiguration() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jwt"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Swagger integration", "Swagger integration", "v1", "",
                new Contact("", "", ""), "",
                "", new ArrayList<VendorExtension>());
    }
}
