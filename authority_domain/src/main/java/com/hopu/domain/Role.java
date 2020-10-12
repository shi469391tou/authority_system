package com.hopu.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色表对应实体类
 */
@TableName("t_role")
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String role; // 角色名称
    private String remark; // 备注


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
        "role=" + role +
        ", remark=" + remark +
        "}";
    }
}
