package com.xhy.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xhy.common.pojo.entity.CmAdminEntity;
import com.xhy.common.pojo.params.CmAdminChangePassParams;
import com.xhy.common.pojo.params.CmAdminLoginParams;
import com.xhy.common.util.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuehy
 * @since 2023/2/15
 */
public interface CmAdminService extends IService<CmAdminEntity> {

    //管理员登录
    Result login(CmAdminLoginParams params, HttpServletRequest request);

    //管理员修改密码
    Result changePass(CmAdminChangePassParams params, HttpServletRequest request);

}
