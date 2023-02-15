package com.xhy.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 分页返回工具类
 * @author xuehy
 * @since 2021/5/20
 */
public class PageResult extends Result {

    public PageResult(IPage page) {
        super(0, "操作成功", page.getRecords());
        //当前页数
        put("pageIndex", page.getCurrent());
        //总页数
        put("pageTotal", page.getPages());
        //每页条数
        put("pageSize", page.getSize());
        //数据总条数
        put("dataTotal", page.getTotal());
    }

}
