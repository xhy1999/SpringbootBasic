package com.xhy.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.xhy.common.annotation.permission.AdminPermission;
import com.xhy.common.pojo.entity.CmSysApiEntity;
import com.xhy.common.service.CmSysApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 系统接口工具类
 * @author xuehy
 * @since 2023/3/8
 */
@Slf4j
@Component
public class SysApiHelper {

    //需要忽略的方法所在包名
    private static final Set<String> IGNORE_PACKAGE_SET = Sets.newHashSet(
            "org.springframework.boot", "springfox.documentation.swagger"
    );

    @Resource
    private CmSysApiService cmSysApiService;

    @Resource
    private WebApplicationContext applicationContext;

    @Bean
    public void syncApiInfo() {
        log.info("开始初始化项目API信息...");
        List<CmSysApiEntity> apiList = new ArrayList<>();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取所有标有@RequestMapping类下面的方法
        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
            CmSysApiEntity apiEntity = new CmSysApiEntity();
            //获取方法对应的RequestMapping信息
            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            //获取类
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();
            //获取类名
            String name = handlerMethod.getMethod().getDeclaringClass().getName();
            //log.info("类名:" + name);
            //log.info("方法名:" + handlerMethod.getMethod().getName());
            if (isIgnore(name)) {
                continue;
            }
            //获取类的所有注解
            //Annotation[] parentAnnotations = handlerMethod.getBeanType().getAnnotations();
            //获取方法的所有注解
            boolean needPermission = false;
            Annotation[] annotations = handlerMethod.getMethod().getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ApiOperation) {
                    apiEntity.setName(StrUtil.subSufByLength(((ApiOperation) annotation).value(), 50));
                } else if (annotation instanceof AdminPermission) {
                    needPermission = true;
                    apiEntity.setCode(((AdminPermission) annotation).value());
                    apiEntity.setKind(StrUtil.subBefore(apiEntity.getCode(), ":", false));
                }
            }
            if (!needPermission) {
                continue;
            }
            //获取请求的URI
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            if (ObjectUtil.isNotNull(p) && ObjectUtil.isNotNull(p.getPatterns())) {
                String uri = ArrayUtil.join(p.getPatterns().toArray(), ",");
                apiEntity.setUri(StrUtil.subSufByLength(uri, 255));
            }
            //获取请求方式
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            if (CollUtil.isEmpty(methodsCondition.getMethods())) {
                apiEntity.setMethod("ALL");
            } else {
                apiEntity.setMethod(ArrayUtil.join(methodsCondition.getMethods().toArray(), ","));
            }
            apiList.add(apiEntity);
            log.info("接口名[{}],接口种类[{}],接口编码[{}],接口地址[{}],请求方式[{}]",
                    apiEntity.getName(), apiEntity.getKind(), apiEntity.getCode(), apiEntity.getUri(), apiEntity.getMethod());
        }
        cmSysApiService.saveOrUpdateBatchApi(apiList);
        log.info("项目API信息初始化完成");
    }

    //判断此包是否需要忽略
    private boolean isIgnore(String packageName) {
        for (String ignorePackageName : IGNORE_PACKAGE_SET) {
            if (packageName.contains(ignorePackageName)) {
                return true;
            }
        }
        return false;
    }

}
