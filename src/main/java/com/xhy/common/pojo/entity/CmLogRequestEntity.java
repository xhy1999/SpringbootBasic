package com.xhy.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 请求日志 Entity
 * @author xuehy
 * @since 2022/11/23
 */
@Data
@TableName("cm_log_request")
public class CmLogRequestEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 请求ip
     */
    private String ip;
    /**
     * 请求uri
     */
    private String uri;
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
