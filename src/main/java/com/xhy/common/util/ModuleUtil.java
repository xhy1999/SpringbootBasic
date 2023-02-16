package com.xhy.common.util;

import cn.hutool.core.util.StrUtil;
import com.xhy.common.constant.CmModuleConst;

/**
 * @author xuehy
 * @since 2021/10/11
 */
public class ModuleUtil {

    /**
     * 获取请求所属模块
     * @param uri 请求路径
     * @return 所属模块
     */
    public static String getModuleByUri(String uri) {
        if (StrUtil.isBlank(uri)) {
            return null;
        }
        if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        for (String module : CmModuleConst.MODULE_SET) {
            if (uri.startsWith(module)) {
                return module;
            }
        }
        return null;
    }

}
