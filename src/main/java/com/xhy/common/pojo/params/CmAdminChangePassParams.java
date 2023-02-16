package com.xhy.common.pojo.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 管理员修改密码 Params
 * @author xuehy
 * @since 2023/2/15
 */
@Data
public class CmAdminChangePassParams {

    //旧密码
    @NotBlank(message = "旧密码不能为空")
    private String oldAdminPass;

    //新密码
    @NotBlank(message = "新密码不能为空")
    private String newAdminPass;

}
