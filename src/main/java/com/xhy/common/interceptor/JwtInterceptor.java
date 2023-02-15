package com.xhy.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.xhy.common.constant.ResultCode;
import com.xhy.common.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * 请求拦截器
 * @author xuehy
 * @since 2022/12/12
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    public static final String TOKEN_HEAD = "X-Auth-Token";

    @Value("${project.is-product}")
    private boolean isProduct;

    //请求黑名单
    private static Set<String> blackSet;
    //请求白名单
    private static Set<String> whiteSet;

    @Resource
    private TokenUtil tokenUtil;

    @Bean
    private void initJwtInterceptor() {
        blackSet = new HashSet<>();
        whiteSet = new HashSet<>();
        if (isProduct) {
            blackSet.add("/swagger-ui.html");
            blackSet.add("/webjars/springfox-swagger-ui/");
            blackSet.add("/null/swagger-resources/");
            blackSet.add("/swagger-resources/");
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        log.info(uri);
        if (uri.equals("/error")) {
            return true;
        }
        if (inBlackSet(uri)) {
            ResponseUtil.writeResponse(Result.fail(ResultCode.ERR_NOT_FOUND, "no route found!"), response);
            return false;
        }
        if (inWhiteSet(uri)) {
            return true;
        }
        String token = request.getHeader(JwtInterceptor.TOKEN_HEAD);
        if (StrUtil.isBlank(token)) {
            ResponseUtil.writeResponse(Result.errParam("缺少请求头A"), response);
            return false;
        }
        String userId = tokenUtil.token2UserId(token), oldToken;
        if (StrUtil.isBlank(userId) || StrUtil.isBlank((oldToken = tokenUtil.getUserToken(userId)))) {
            ResponseUtil.writeResponse(Result.fail(ResultCode.ERR_TOKEN, "Token失效"), response);
            return false;
        }
        if (!oldToken.equals(token)) {
            ResponseUtil.writeResponse(Result.fail(ResultCode.ERR_TOKEN, "此账号在别处登录"), response);
            return false;
        } else {
            tokenUtil.refreshTokenExpire(userId);
            return true;
        }
    }

    private boolean inBlackSet(String uri) {
        for (String black : blackSet) {
            if (uri.contains(black)) {
                return true;
            }
        }
        return false;
    }

    private boolean inWhiteSet(String uri) {
        for (String white : whiteSet) {
            if (uri.contains(white)) {
                return true;
            }
        }
        return false;
    }

}

