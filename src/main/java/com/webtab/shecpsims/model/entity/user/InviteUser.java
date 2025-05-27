package com.webtab.shecpsims.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.ToString;

@ToString
@TableName("InviteUser")
public class InviteUser {
    @TableId(type = IdType.AUTO)
    private Integer  id;
    private String userName;
    private String password;
    private String inviteCode;
    private int connectUserId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public void setConnectUserId(int connectUserId) {
        this.connectUserId = connectUserId;
    }
    public int getConnectUserId() {
        return connectUserId;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}
