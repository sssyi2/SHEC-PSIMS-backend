package com.webtab.shecpsims.model.vo.user;

import lombok.Data;

@Data
public class LoginUserVO {
    private int userID; // 用户ID

    private String username; // 用户名

    private String password; // 密码

    private String email; // 邮箱

    private String phoneNumber; // 电话号码

    private String realName; // 真实姓名

    private String department; // 部门
}