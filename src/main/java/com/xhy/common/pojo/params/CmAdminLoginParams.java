package com.xhy.common.pojo.params;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 管理员登录 Params
 * @author xuehy
 * @since 2023/2/15
 */
@Data
public class CmAdminLoginParams {

    @NotBlank(message = "账号不能为空")
    @Length(min = 5, max = 18, message = "账号长度必须为5-18位")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

}
