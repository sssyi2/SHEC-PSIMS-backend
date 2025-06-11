package com.webtab.shecpsims.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateRequest implements Serializable {

    private int userID;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String real_name;

    private String department;


}