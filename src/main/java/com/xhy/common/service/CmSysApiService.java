package com.xhy.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhy.common.pojo.entity.CmSysApiEntity;

import java.util.List;

/**
 * @author xuehy
 * @since 2023/3/13
 */
public interface CmSysApiService extends IService<CmSysApiEntity> {

    //批量保存或更新API
    void saveOrUpdateBatchApi(List<CmSysApiEntity> apiList);

}
