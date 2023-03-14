package com.xhy.common.controller;

import com.xhy.common.annotation.operate.OperateLog;
import com.xhy.common.annotation.permission.AdminPermission;
import com.xhy.common.constant.CmOperateType;
import com.xhy.common.pojo.params.CmAdminChangePassParams;
import com.xhy.common.pojo.params.CmAdminLoginParams;
import com.xhy.common.service.CmAdminService;
import com.xhy.common.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author xuehy
 * @since 2023/2/15
 */
@Slf4j
@Api(tags = "管理员相关接口")
@RestController
@RequestMapping("/common/admin/api/")
public class CmAdminController {

    @Resource
    private CmAdminService cmAdminService;

    @PostMapping("login")
    @ApiOperation("管理员登录")
    public Result login(@RequestBody @Valid CmAdminLoginParams params, HttpServletRequest request) {
        return cmAdminService.login(params, request);
    }

    @PostMapping("changePass")
    @AdminPermission("cmAdmin:changePass")
    @ApiOperation("管理员修改密码")
    @OperateLog(type = CmOperateType.UPDATE, desc = "管理员修改密码")
    public Result changePass(@RequestBody @Valid CmAdminChangePassParams params, HttpServletRequest request) {
        return cmAdminService.changePass(params, request);
    }

}
