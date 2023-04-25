package com.preProject.MyStackOverFlow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private String version = "v1.0.0";

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Stackoverflow Clone Project")
                .description(" 이 문서는 Frontend(곽미소, 정은옥, 임태영)분들과 Backend(서현준, 천찬웅, 김재윤)분들이 함께 진행하는 StackOverFlow Clone 프로젝트입니다.\n" +
                        "이 애플리케이션을 사용해보고자 하는 분들은 이 문서를 통해 API의 구체적인 사용법을 알 수 있습니다.")
                .version(version)
                .contact(new Contact("김재윤", "", "wkddkgodsla@gmail.com"))
                .build();
    }
}
