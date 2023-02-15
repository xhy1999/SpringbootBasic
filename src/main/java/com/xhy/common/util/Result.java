package com.xhy.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.xhy.common.constant.ResultCode;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 返回值 code必须为int message为信息 data为请求成功后返回的数据
 * @author xuehy
 * @since 2021/5/20
 */
public class Result extends HashMap<String, Object> implements Serializable {

    private static final String CODE = "code";
    private static final String DATA = "data";
    private static final String MESSAGE = "message";

    private static final long serialVersionUID = 1L;

    public Result() {

    }

    public Result(Object data) {
        put(DATA, data);
    }

    public Result(int code, String msg) {
        put(CODE, code);
        put(MESSAGE, msg);
    }

    public Result(int code, String msg, Object data) {
        put(CODE, code);
        put(MESSAGE, msg);
        put(DATA, data);
    }

    public String getMessage() {
        Object message = this.get(MESSAGE);
        return ObjectUtil.isNull(message) ? null : ObjectUtil.toString(message);
    }

    public boolean isSuccess() {
        if (this.get(CODE) instanceof Integer) {
            return (int) this.get(CODE) == ResultCode.SUCCESS;
        }
        return false;
    }

    public boolean isFail() {
        return !isSuccess();
    }

    public static Result success() {
        Result r = new Result();
        r.put(CODE, ResultCode.SUCCESS);
        r.put(MESSAGE, "操作成功");
        return r;
    }

    public static Result success(Object data) {
        Result r = new Result();
        r.put(CODE, ResultCode.SUCCESS);
        r.put(DATA, data);
        r.put(MESSAGE, "操作成功");
        return r;
    }

    public static Result success(String msg, Object data) {
        Result r = new Result();
        r.put(CODE, ResultCode.SUCCESS);
        r.put(DATA, data);
        r.put(MESSAGE, msg);
        return r;
    }

    public static Result fail() {
        return fail(ResultCode.ERR, "发生错误,请联系管理员");
    }

    public static Result fail(String msg) {
        return fail(ResultCode.ERR, msg);
    }

    public static Result fail(int code, String msg) {
        Result r = new Result();
        r.put(CODE, code);
        r.put(MESSAGE, msg);
        return r;
    }

    public static Result errParam(String msg) {
        return fail(ResultCode.ERR_PARAM, msg);
    }

}
