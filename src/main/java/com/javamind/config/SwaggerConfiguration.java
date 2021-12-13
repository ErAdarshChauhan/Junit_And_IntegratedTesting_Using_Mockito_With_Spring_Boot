package com.javamind.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket employeeApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Java Mind")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.javamind"))
                .paths(regex("/api/employee.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employee Service")
                .description("Sample Documentation Generated Using SWAGGER_2 for our Rest API")
                .termsOfServiceUrl("https://www.youtube.com")
                .license("Java_Gyan_Mantra License")
                .licenseUrl("https://www.youtube.com").version("1.0")
                .build();

    }

}
