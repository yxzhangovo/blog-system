package pers.blog.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author: zyx
 * @create: 2023/9/2
 * @description: Swagger配置类
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("pers.blog.controller"))
                .build();

    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("zyx", "https://github.com/zyx0316/blog-system", "yxzhang0316@outlook.com");
        return new ApiInfoBuilder()
                .title("Project Api")
                .description("前台页面测试接口")
                .contact(contact)   // 联系方式
                .version("1.1.0")  // 版本
                .build();
    }
}
