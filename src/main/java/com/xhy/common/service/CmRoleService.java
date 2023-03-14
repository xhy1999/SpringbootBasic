package com.xhy.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhy.common.pojo.entity.CmRoleEntity;
import com.xhy.common.pojo.params.CmRoleQueryParams;
import com.xhy.common.util.Result;

/**
 * @author xuehy
 * @since 2023/3/7
 */
public interface CmRoleService extends IService<CmRoleEntity> {

    //分页获取角色列表
    Result getRole(CmRoleQueryParams params);

}
