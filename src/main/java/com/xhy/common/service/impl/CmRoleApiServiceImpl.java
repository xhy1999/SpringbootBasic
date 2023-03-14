package com.xhy.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhy.common.mapper.CmRoleApiMapper;
import com.xhy.common.mapper.CmRoleMapper;
import com.xhy.common.pojo.entity.CmRoleApiEntity;
import com.xhy.common.pojo.entity.CmRoleEntity;
import com.xhy.common.service.CmRoleApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author xuehy
 * @since 2023/3/13
 */
@Slf4j
@Service
public class CmRoleApiServiceImpl extends ServiceImpl<CmRoleApiMapper, CmRoleApiEntity> implements CmRoleApiService {

    //角色权限缓存
    private static final ConcurrentHashMap<Integer, Set<String>> ROLE_API_CACHE = new ConcurrentHashMap<>();

    @Resource
    private CmRoleMapper cmRoleMapper;

    @Override
    public boolean checkPermission(Integer roleId, String apiCode) {
        try {
            return ROLE_API_CACHE.get(roleId).contains(apiCode);
        } catch (NullPointerException ignored) {
            return false;
        }
    }

    @Override
    public void refreshRoleApiCache(Integer roleId) {
        Set<Integer> roleIdSet = new HashSet<>();
        roleIdSet.add(roleId);
        this.refreshRoleApiCache(roleIdSet);
    }

    @Override
    public void refreshRoleApiCache(Set<Integer> roleIdSet) {
        //先查询存在的角色id
        roleIdSet = cmRoleMapper.selectList(
                Wrappers.<CmRoleEntity>lambdaQuery()
                        .select(CmRoleEntity::getId)
                        .in(CollUtil.isNotEmpty(roleIdSet), CmRoleEntity::getId, roleIdSet)
                        .eq(CmRoleEntity::getEnabled, CmRoleEntity.ENABLED)
        ).stream().map(CmRoleEntity::getId).collect(Collectors.toSet());
        if (CollUtil.isEmpty(roleIdSet)) {
            return;
        }
        Map<Integer, List<CmRoleApiEntity>> roleApiMap = this.baseMapper.selectList(
                Wrappers.<CmRoleApiEntity>lambdaQuery()
                        .in(CmRoleApiEntity::getRoleId, roleIdSet)
        ).stream().collect(Collectors.groupingBy(CmRoleApiEntity::getRoleId));
        for (Map.Entry<Integer, List<CmRoleApiEntity>> entry : roleApiMap.entrySet()) {
            Integer roleId = entry.getKey();
            Set<String> apiCodeSet = entry.getValue().stream().map(CmRoleApiEntity::getApiCode).collect(Collectors.toSet());
            if (ObjectUtil.isNotNull(roleId) && ObjectUtil.isNotNull(apiCodeSet)) {
                ROLE_API_CACHE.put(roleId, apiCodeSet);
            }
        }
    }

}
