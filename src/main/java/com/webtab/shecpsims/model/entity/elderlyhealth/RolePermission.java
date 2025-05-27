package com.webtab.shecpsims.model.entity.elderlyhealth;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
