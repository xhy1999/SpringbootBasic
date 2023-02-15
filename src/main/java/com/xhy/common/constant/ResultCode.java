package com.xhy.common.constant;

/**
 * 返回值常数
 * @author xuehy
 * @since 2021/5/20
 */
public interface ResultCode {

    //成功
    int SUCCESS = 0;
    //失败
    int ERR = 500;

    //错误-参数
    int ERR_PARAM = 400;
    //错误-页面不存在
    int ERR_NOT_FOUND = 404;

    //Token无效
    int ERR_TOKEN = -100;

}
