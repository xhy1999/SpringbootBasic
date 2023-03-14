package com.xhy.common.exception;

import com.xhy.common.constant.ResultCode;

/**
 * 无此接口权限异常
 * @author xuehy
 * @since 2023/3/13
 */
public class ApiForbiddenException extends CustomException {

    public ApiForbiddenException() {
        super(ResultCode.ERR_FORBIDDEN, "无此接口权限");
    }

}
