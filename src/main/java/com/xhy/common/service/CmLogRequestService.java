package com.xhy.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhy.common.pojo.entity.CmLogRequestEntity;
import com.xhy.common.util.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuehy
 * @since 2023/2/15
 */
public interface CmLogRequestService extends IService<CmLogRequestEntity> {

    //记录请求
    Result recordRequest(HttpServletRequest request);

}
