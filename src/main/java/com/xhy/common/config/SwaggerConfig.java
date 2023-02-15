package com.xhy.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 配置
 * @author xuehy
 * @since 2021/5/20
 */
@EnableSwagger2
@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Value("${project.is-product}")
    private boolean isProduct;

    @Bean
    public Docket commonDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(commonApiInfo())
                .enable(!isProduct)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xhy.common.controller"))
                .paths(PathSelectors.ant("/common/**"))
                .build()
                .groupName("公共模块");
    }

    public ApiInfo commonApiInfo() {
        return new ApiInfoBuilder()
                .title("公共模块相关 API")
                .description("")
                .termsOfServiceUrl("")
                .version("0.1")
                .build();
    }

}
