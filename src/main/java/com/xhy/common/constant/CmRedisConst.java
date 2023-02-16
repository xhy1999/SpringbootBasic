package com.xhy.common.constant;

/**
 * Redis 常量
 * @author xuehy
 * @since 2021/9/13
 */
public interface CmRedisConst {

    //Token有效时间(30min)
    Integer TOKEN_EXPIRES_MINUTE = 30;

    //************************************ Key

    //Redis Key:用户信息前缀
    String ADMIN_KEY_PREFIX = "ADMIN_";

    //Redis HKey:用户Token
    String TOKEN = "TOKEN";

    //Redis HKey:是否为超级管理员
    String IS_SUPER_ADMIN = "IS_SUPER_ADMIN";

}
