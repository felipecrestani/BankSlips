package com.bankslipsrest.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public String getTitle() {
        return "Bank Slips API";
    }

    public String getDescription(){
        return "Bank Slips API";
    }

    public String getVersion(){
        return "V1";
    }

    public String getTermsOfService() {
        return "Terms of Service";
    }

    public String getLicenseUrl() {
        return "https://www.apache.org/licenses/LICENSE-2.0";
    }

    public String getLicense() {
        return "Test Company S/A";
    }

    public String getBasePackage(){
        return "com.bankslipsrest";
    }

    public Contact getContact(){
        return null;
    }

    @Bean
    public Docket apis() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(getBasePackage()))
            .paths(PathSelectors.any())
            .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            getTitle(),
            getDescription(),
            getVersion(),
            getTermsOfService(),
            getContact(),
            getLicense(), getLicenseUrl(), Collections.emptyList());
    }

    @Bean
    UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .validatorUrl("")
                .build();
    }

}