package com.xhy.common.pojo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * 系统接口 Entity
 * @author xuehy
 * @since 2023/3/7
 */
@Data
@TableName("cm_sys_api")
public class CmSysApiEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 种类
     */
    private String kind;
    /**
     * 接口编码,唯一,对应注解中的编码,格式为-种类:标识
     */
    private String code;
    /**
     * 接口名称,对应swagger注解中的名称
     */
    private String name;
    /**
     * 接口地址
     */
    private String uri;
    /**
     * 请求方式
     */
    private String method;
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
