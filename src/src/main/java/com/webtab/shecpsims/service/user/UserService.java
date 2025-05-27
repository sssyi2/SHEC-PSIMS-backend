package com.webtab.shecpsims.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webtab.shecpsims.model.entity.user.User;

public interface UserService extends IService<User> {
    public User getUserById(int UserID);

    User  getUserByUsername(String userName);

    User identityInvite(String inviteCode);

}
