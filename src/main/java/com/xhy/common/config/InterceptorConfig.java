package com.xhy.common.config;

import com.xhy.common.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author xuehy
 * @since 2022/11/24
 */
@Slf4j
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${project.is-product}")
    private boolean isProduct;

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (isProduct) {
            //生产环境
            registry.addInterceptor(jwtInterceptor)
                    //公共模块
                    .addPathPatterns("/common/**")
                    //登录接口无需鉴权
                    .excludePathPatterns("/common/admin/api/login")
                    .addPathPatterns("/swagger-ui.html")
                    .addPathPatterns("/webjars/springfox-swagger-ui/**")
                    .addPathPatterns("/null/swagger-resources/**")
                    .addPathPatterns("/swagger-resources/**");
        } else {
            //开发环境
            registry.addInterceptor(jwtInterceptor)
                    //公共模块
                    .addPathPatterns("/common/**")
                    //登录接口无需鉴权
                    .excludePathPatterns("/common/admin/api/login")
                    .excludePathPatterns("/swagger-ui.html")
                    .excludePathPatterns("/webjars/springfox-swagger-ui/**")
                    .excludePathPatterns("/null/swagger-resources/**")
                    .excludePathPatterns("/swagger-resources/**");
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
