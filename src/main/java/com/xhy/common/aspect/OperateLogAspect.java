package com.xhy.common.aspect;

import com.xhy.common.annotation.OperateLog;
import com.xhy.common.mapper.CmLogAdminOperateMapper;
import com.xhy.common.pojo.entity.CmLogAdminOperateEntity;
import com.xhy.common.util.IPUtil;
import com.xhy.common.util.ModuleUtil;
import com.xhy.common.util.TokenAdminHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author xuehy
 * @since 2021/10/11
 */
@Aspect
@Component
public class OperateLogAspect {

    @Resource
    private CmLogAdminOperateMapper cmLogAdminOperateMapper;

    @Resource
    private TokenAdminHelper tokenAdminHelper;

    @Pointcut("@annotation(com.xhy.common.annotation.OperateLog)")
    public void OperateLogPointcut() {

    }

    @AfterReturning(value = "OperateLogPointcut()")
    public void saveOperateLog(JoinPoint joinPoint) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        CmLogAdminOperateEntity entity = new CmLogAdminOperateEntity();
        try {
            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            OperateLog opLog = method.getAnnotation(OperateLog.class);
            if (opLog != null) {
                entity.setDesc(opLog.desc());
                entity.setType(opLog.type());
            }
            entity.setAdminId(tokenAdminHelper.request2AdminId(request));
            entity.setUri(request.getRequestURI());
            entity.setModule(ModuleUtil.getModuleByUri(entity.getUri()));
            entity.setIp(IPUtil.getIpAddr(request));
            cmLogAdminOperateMapper.insert(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
