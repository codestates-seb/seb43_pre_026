package com.preProject.MyStackOverFlow.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Stackoverflow Clone Project",
                description = " 이 문서는 Frontend(곽미소, 정은옥, 임태영)분들과 Backend(서현준, 천찬웅, 김재윤)분들이 함께 진행하는 StackOverFlow Clone 프로젝트입니다.\n" +
                        "이 애플리케이션을 사용해보고자 하는 분들은 이 문서를 통해 API의 구체적인 사용법을 알 수 있습니다.",
                version = "v1.0.0",
                contact = @Contact(
                        name = "김재윤",
                        email = "wkddkgodsla@gmail.com"
                )
        )
)
@Configuration
public class OpenApiConfig {

//    @Bean
//    public OpenAPI openAPI() {
//
//        Info info = new Info()
//                .version("v1.0.0")
//                .title("Stackoverflow Clone Project")
//                .description(" 이 문서는 Frontend(곽미소, 정은옥, 임태영)분들과 Backend(서현준, 천찬웅, 김재윤)분들이 함께 진행하는 StackOverFlow Clone 프로젝트입니다.\n" +
//                        "이 애플리케이션을 사용해보고자 하는 분들은 이 문서를 통해 API의 구체적인 사용법을 알 수 있습니다.");
//
//        return new OpenAPI()
//                .info(info);
//    }
}
