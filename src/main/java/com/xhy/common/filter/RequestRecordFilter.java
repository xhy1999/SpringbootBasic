package com.xhy.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求过滤器,保存所有的请求记录
 * @author xuehy
 * @since 2021/9/3
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class RequestRecordFilter implements Filter {

    //@Resource
    //private CmLogRequestService cmLogRequestService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            //cmLogRequestService.recordRequest(request);   TODO 记录请求
            filterChain.doFilter(request, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
