package com.xhy.common.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * 系统模块
 * @author xuehy
 * @since 2021/9/6
 */
public class CmModuleConst {

    public static Set<String> MODULE_SET;

    static {
        MODULE_SET = new HashSet<>();
        //公共模块
        MODULE_SET.add("common");
    }

}
