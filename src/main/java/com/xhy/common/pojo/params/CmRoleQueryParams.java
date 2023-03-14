package com.xhy.common.pojo.params;

import lombok.Data;

/**
 * 查询管理员权限 Params
 * @author xuehy
 * @since 2023/3/7
 */
@Data
public class CmRoleQueryParams extends PageParams {

    //角色名称
    private String name;

}
