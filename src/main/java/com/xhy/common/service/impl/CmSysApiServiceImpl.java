package com.xhy.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhy.common.mapper.CmSysApiMapper;
import com.xhy.common.pojo.entity.CmSysApiEntity;
import com.xhy.common.service.CmSysApiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xuehy
 * @since 2023/3/13
 */
@Service
public class CmSysApiServiceImpl extends ServiceImpl<CmSysApiMapper, CmSysApiEntity> implements CmSysApiService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateBatchApi(List<CmSysApiEntity> apiList) {
        if (CollUtil.isEmpty(apiList)) {
            return;
        }
        //<apiCode, apiId>
        Map<String, Integer> existedMap = this.baseMapper.selectList(
                Wrappers.<CmSysApiEntity>lambdaQuery()
                        .in(CmSysApiEntity::getCode, apiList.stream().map(CmSysApiEntity::getCode).collect(Collectors.toSet()))
        ).stream().collect(Collectors.toMap(CmSysApiEntity::getCode, CmSysApiEntity::getId));
        for (CmSysApiEntity apiEntity : apiList) {
            Integer id = existedMap.get(apiEntity.getCode());
            if (ObjectUtil.isNotNull(id)) {
                apiEntity.setId(id);
            }
        }
        //保存或更新API信息
        this.saveOrUpdateBatch(apiList);
        //不存在的API先不删除
    }

}
