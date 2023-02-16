package com.xhy.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 管理员用户操作日志 Entity
 * @author xuehy
 * @since 2021/10/11
 */
@Data
@TableName("cm_log_admin_operate")
public class CmLogAdminOperateEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 管理员id
     */
    private String adminId;
    /**
     * 操作模块
     */
    private String module;
    /**
     * ip
     */
    private String ip;
    /**
     * uri
     */
    private String uri;
    /**
     * 操作类型 1.新增 2.删除 3.修改 4.查询
     */
    private String type;
    /**
     * 操作描述
     */
    @TableField("`desc`")
    private String desc;
    /**
     * 创建时间(请求时间)
     */
    private Date createTime;
    /**
     * 是否删除: 0.未删除 1.已删除
     */
    @TableLogic
    private String isDel;

}
