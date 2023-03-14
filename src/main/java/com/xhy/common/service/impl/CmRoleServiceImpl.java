package com.xhy.common.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhy.common.mapper.CmRoleMapper;
import com.xhy.common.pojo.entity.CmRoleEntity;
import com.xhy.common.pojo.params.CmRoleQueryParams;
import com.xhy.common.service.CmRoleService;
import com.xhy.common.util.PageResult;
import com.xhy.common.util.PageUtil;
import com.xhy.common.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author xuehy
 * @since 2023/3/7
 */
@Service
public class CmRoleServiceImpl extends ServiceImpl<CmRoleMapper, CmRoleEntity> implements CmRoleService {

    @Override
    public Result getRole(CmRoleQueryParams params) {
        Page<CmRoleEntity> resPage = this.baseMapper.selectPage(
                PageUtil.getPage(params),
                Wrappers.<CmRoleEntity>lambdaQuery()
                        .like(StrUtil.isNotBlank(params.getName()), CmRoleEntity::getName, params.getName())
        );
        return new PageResult(resPage);
    }

}
