package com.xhy.common.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * @author xuehy
 * @since 2021/10/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

    //操作类型
    String type() default "";

    //操作说明
    String desc() default "";

    //是否记录参数
    boolean saveParams() default false;

}
