package com.xhy.common.filter;

import com.xhy.common.service.CmLogRequestService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
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

    @Resource
    private CmLogRequestService cmLogRequestService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            //TODO 这里记录所有请求 也可以不记录
            cmLogRequestService.recordRequest(request);
            filterChain.doFilter(request, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
