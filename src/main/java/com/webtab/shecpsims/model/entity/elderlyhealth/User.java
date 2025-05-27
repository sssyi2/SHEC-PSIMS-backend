package com.webtab.shecpsims.model.entity.elderlyhealth;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
//用户实体类
public class User implements Serializable {
    private int UserID;
    private String UserName;
    private String PasswordHash;
    private String Email;
    private String PhoneNumber;
    private String realName;
    private String department;
}
