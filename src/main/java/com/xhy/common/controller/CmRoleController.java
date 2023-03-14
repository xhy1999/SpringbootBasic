package com.xhy.common.controller;

import com.xhy.common.annotation.operate.OperateLog;
import com.xhy.common.annotation.permission.AdminPermission;
import com.xhy.common.constant.CmOperateType;
import com.xhy.common.pojo.params.CmRoleQueryParams;
import com.xhy.common.service.CmRoleService;
import com.xhy.common.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author xuehy
 * @since 2023/2/15
 */
@Slf4j
@Api(tags = "管理员角色相关接口")
@RestController
@RequestMapping("/common/role/api/")
public class CmRoleController {

    @Resource
    private CmRoleService cmRoleService;

    @PostMapping("getRole")
    @AdminPermission("cmAdmin:getRole")
    @ApiOperation("分页获取角色列表")
    @OperateLog(type = CmOperateType.ADD, desc = "分页获取角色列表")
    public Result getRole(@RequestBody @Valid CmRoleQueryParams params) {
        return cmRoleService.getRole(params);
    }

}
