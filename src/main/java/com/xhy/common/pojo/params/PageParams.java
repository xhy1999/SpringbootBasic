package com.xhy.common.pojo.params;

import lombok.Data;

/**
 * 分页参数
 * @author xuehy
 * @since 2021/4/22
 */
@Data
public class PageParams {

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
            this.pageIndex = 1L;
        }
        if (this.pageSize == null || this.pageSize < 1) {
            this.pageSize = 10L;
        }
        return this;
    }

}
