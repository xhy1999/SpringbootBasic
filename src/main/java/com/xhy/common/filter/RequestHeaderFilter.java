package com.xhy.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求头配置
 * @author xuehy
 * @since 2021/12/15
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class RequestHeaderFilter implements Filter {

    @Value("${project.is-product}")
    private boolean isProduct;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //安全性配置
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-XSS-Protection", "1");
        if (isProduct) {
            response.setHeader("Content-Security-Policy", "default-src 'self';frame-ancestors 'none';");
        } else {
            response.setHeader("Content-Security-Policy", "frame-ancestors 'none';");
        }
        response.setHeader("X-Frame-Options", "DENY");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(200);
            return;
        }
        filterChain.doFilter(request, response);
    }

}
