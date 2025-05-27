//package com.webtab.medicalsystem.service;
//
//import com.webtab.medicalsystem.entity.Permission;
//import com.webtab.medicalsystem.entity.RolePermission;
//import com.webtab.medicalsystem.entity.UserRole;
//import com.webtab.medicalsystem.mapper.PermissionMapper;
//import com.webtab.medicalsystem.mapper.RolePermissionMapper;
//import com.webtab.medicalsystem.mapper.UserRoleMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PermissionService {
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;
//
//    @Autowired
//    private RolePermissionMapper rolePermissionMapper;
//
//    @Autowired
//    private PermissionMapper permissionMapper;
//
//    public boolean hasPermission(Integer userId, String permissionName) {
//        List<UserRole> userRoles = userRoleMapper.selectUserRolesByUserId(userId);
//        for (UserRole userRole : userRoles) {
//            Integer roleId = userRole.getRoleId();
//            List<RolePermission> rolePermissions = rolePermissionMapper.selectRolePermissionsByRoleId(roleId);
//            for (RolePermission rolePermission : rolePermissions) {
//                Integer permissionId = rolePermission.getPermissionId();
//                Permission permission = permissionMapper.selectPermissionById(permissionId);
//                if (permission != null && permission.getPermissionName().equals(permissionName)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//}