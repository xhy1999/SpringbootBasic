package com.xhy.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhy.common.pojo.entity.CmRoleApiEntity;

import java.util.Set;

/**
 * @author xuehy
 * @since 2023/3/13
 */
public interface CmRoleApiService extends IService<CmRoleApiEntity> {

    //验证角色权限
    boolean checkPermission(Integer roleId, String apiCode);

    //刷新角色权限缓存
    void refreshRoleApiCache(Integer roleId);

    //刷新角色权限缓存
    void refreshRoleApiCache(Set<Integer> roleIdSet);

}
