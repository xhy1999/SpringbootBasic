package com.xhy.common.aspect.permission;

import cn.hutool.core.util.ObjectUtil;
import com.xhy.common.annotation.permission.AdminPermission;
import com.xhy.common.exception.ApiForbiddenException;
import com.xhy.common.service.CmRoleApiService;
import com.xhy.common.util.RequestUtil;
import com.xhy.common.util.TokenAdminHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限验证切面
 * @author xuehy
 * @since 2023/3/10
 */
@Slf4j
@Aspect
@Component
public class AdminPermissionAspect {

    @Resource
    private CmRoleApiService cmRoleApiService;

    @Resource
    private TokenAdminHelper tokenAdminHelper;

    @Pointcut("@annotation(com.xhy.common.annotation.permission.AdminPermission)")
    public void AdminPermissionPointcut() {

    }

    @Before(value = "AdminPermissionPointcut()")
    public void checkPermission(JoinPoint joinPoint) {
        log.info("开始验证权限...");
        HttpServletRequest request = RequestUtil.getRequest();
        AdminPermission annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AdminPermission.class);
        Integer roleId = tokenAdminHelper.request2RoleId(request);
        if (ObjectUtil.isNull(roleId) || !cmRoleApiService.checkPermission(roleId, annotation.value())) {
            log.info("角色[{}],无接口[{}]权限", roleId, annotation.value());
            //抛出无此接口权限异常
            throw new ApiForbiddenException();
        }
        log.info("权限验证通过");
    }

}
