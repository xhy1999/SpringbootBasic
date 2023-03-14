package com.xhy.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色接口权限 Entity
 * @author xuehy
 * @since 2023/3/13
 */
@Data
@TableName("cm_role_api")
public class CmRoleApiEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 接口编码
     */
    private String apiCode;

}
