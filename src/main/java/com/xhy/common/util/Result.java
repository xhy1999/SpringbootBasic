package com.xhy.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
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

    public String getStrData() {
        Object data = this.get(DATA);
        if (data == null) {
            return null;
        }
        if (data instanceof String) {
            return (String) data;
        }
        return ObjectUtil.toString(data);
    }

    public String getStrMessage() {
        Object message = this.get(MESSAGE);
        if (message == null) {
            return null;
        }
        if (message instanceof String) {
            return (String) message;
        }
        return ObjectUtil.toString(message);
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

    public static Result errParam(String template, Object... params) {
        return fail(ResultCode.ERR_PARAM, StrUtil.format(template, params));
    }

    public static Result errEmptyParam() {
        return errParam("参数不能为空");
    }

    public static Result dataNotExist() {
        return errParam("数据不存在");
    }

    public static Result errDelParam() {
        return errParam("无有效的删除数据");
    }

    public static Result errUploadFile() {
        return fail("文件上传失败");
    }

    public static Result errFileType(String type) {
        return errParam("不支持的文件类型:{}", type);
    }

    public static Result errFileSize(Integer maxSize) {
        return errParam("文件大小超过上限({}MB)", maxSize);
    }

}
