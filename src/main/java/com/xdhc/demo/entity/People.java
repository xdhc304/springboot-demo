package com.xdhc.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tb_user")
public class People {

    /**
     * @TableId和@TableField 不能同时使用，如果数据库中主键名称是id，实体属性是userId，你可以这样映射@TableId("id")
     * <p>
     * 主键策略6种：
     * AUTO(0),
     * NONE(1),
     * INPUT(2),
     * ID_WORKER(3),
     * UUID(4),
     * ID_WORKER_STR(5);
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    @TableField("name")
    private String realName;

    private Integer age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    /**
     * 排除非表字段的三种方式
     * 1、private transient String remark; 无法序列化
     * 2、private static String remark; 实例无法独有一份
     * 3、@TableField(exist=false)
     */
    @TableField(exist = false)
    private String remark;

    @TableLogic
    private Integer deleted;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
