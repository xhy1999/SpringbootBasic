package com.xhy.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhy.common.mapper.CmLogRequestMapper;
import com.xhy.common.pojo.entity.CmLogRequestEntity;
import com.xhy.common.service.CmLogRequestService;
import com.xhy.common.util.IPUtil;
import com.xhy.common.util.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuehy
 * @since 2023/2/15
 */
@Service
public class CmLogRequestServiceImpl extends ServiceImpl<CmLogRequestMapper, CmLogRequestEntity> implements CmLogRequestService {

    @Override
    public Result recordRequest(HttpServletRequest request) {
        CmLogRequestEntity saveEntity = new CmLogRequestEntity();
        saveEntity.setIp(IPUtil.getIpAddr(request));
        saveEntity.setUri(request.getRequestURI());
        this.save(saveEntity);
        return Result.success();
    }

}
