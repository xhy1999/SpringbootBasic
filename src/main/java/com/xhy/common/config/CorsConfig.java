package com.xhy.common.config;

import com.xhy.common.interceptor.JwtInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * 跨域配置
 * @author xuehy
 * @since 2021/12/15
 */
@Configuration
public class CorsConfig {

    private static List<String> ALLOWED_METHODS = new ArrayList<>();
    private static List<String> ALLOWED_HEADERS = new ArrayList<>();

    static {
        ALLOWED_METHODS.add("GET");
        ALLOWED_METHODS.add("POST");
        ALLOWED_METHODS.add("PUT");
        ALLOWED_METHODS.add("DELETE");
        ALLOWED_METHODS.add("OPTIONS");
        ALLOWED_HEADERS.add("Accept");
        ALLOWED_HEADERS.add("Origin");
        ALLOWED_HEADERS.add("Content-Type");
        ALLOWED_HEADERS.add("X-Requested-With");
        ALLOWED_HEADERS.add(JwtInterceptor.TOKEN_HEAD);
    }

    @Bean
    public FilterRegistrationBean<Filter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.setAllowedMethods(ALLOWED_METHODS);
        config.setAllowedHeaders(ALLOWED_HEADERS);
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}