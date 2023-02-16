package com.xhy.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 管理员用户 Entity
 * @author xuehy
 * @since 2023/2/15
 */
@Data
@TableName("cm_admin")
public class CmAdminEntity {

    //启用管理员
    public static final String ENABLED = "1";
    //禁用管理员
    public static final String DISABLED = "0";

    /**
     * 管理员id 以ad_开头 共15位
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 管理员名称
     */
    private String name;
    /**
     * 登录账号
     */
    private String account;
    /**
     * 密码 算法:sha256(id+password(明文))
     */
    private String password;
    /**
     * 是否为超级管理员 0.否 1.是
     */
    private String isSuperAdmin;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 地址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否启用: 0.禁用 1.启用
     */
    private String enabled;
    /**
     * 上次登录ip
     */
    private String lastLoginIp;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除: 0.未删除 1.已删除
     */
    @TableLogic
    private String isDel;

}

