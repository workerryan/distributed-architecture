package com.shop.common.core.base.entity;

import java.util.Date;

public class BaseEntity {
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 修改时间
     *
     */
    private Date updateTime;
    /**
     * id
     */
    private Long id;

    /**
     * 是否可用 0可用 1不可用
     */
    private Long isAvailability;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsAvailability() {
        return isAvailability;
    }

    public void setIsAvailability(Long isAvailability) {
        this.isAvailability = isAvailability;
    }
}
