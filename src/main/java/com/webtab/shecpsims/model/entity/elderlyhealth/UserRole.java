package com.webtab.shecpsims.model.entity.elderlyhealth;

import lombok.Data;
import lombok.ToString;

@ToString
public class UserRole {

    private Integer userId;
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
