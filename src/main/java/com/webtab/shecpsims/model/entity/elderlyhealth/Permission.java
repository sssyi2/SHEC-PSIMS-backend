package com.webtab.shecpsims.model.entity.elderlyhealth;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Permission {

    private Integer permissionId;
    private String permissionName;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
