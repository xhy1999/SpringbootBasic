package com.xhy.common.pojo.params;

import lombok.Data;

/**
 * 分页参数
 * @author xuehy
 * @since 2021/4/22
 */
@Data
public class PageParams {

    //默认分页参数
    public static final long DEFAULT_PAGE_INDEX = 1L;
    public static final long DEFAULT_PAGE_SIZE = 10L;

    //请求页数
    private Long pageIndex;

    //每页大小
    private Long pageSize;

    public PageParams() {
        initPage();
    }

    //初始化分页值
    public PageParams initPage() {
        if (this.pageIndex == null || this.pageIndex < 1) {
            this.pageIndex = DEFAULT_PAGE_INDEX;
        }
        if (this.pageSize == null || this.pageSize < 1) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        return this;
    }

}
