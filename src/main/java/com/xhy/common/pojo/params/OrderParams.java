package com.xhy.common.pojo.params;

import lombok.Data;

import java.util.List;

/**
 * 排序参数
 * @author xuehy
 * @since 2022/2/15
 */
@Data
public class OrderParams extends PageParams {

    //升序
    private List<String> orderBy;

    //判断是否需要排序
    public boolean needOrder() {
        return orderBy != null && !orderBy.isEmpty();
    }

}
