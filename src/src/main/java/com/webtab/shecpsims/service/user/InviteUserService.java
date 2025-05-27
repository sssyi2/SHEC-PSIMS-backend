package com.webtab.shecpsims.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webtab.shecpsims.model.entity.user.InviteUser;

public interface InviteUserService extends IService<InviteUser> {
    String getInviteCode();
    InviteUser isUsedInviteCode(String inviteCode);
}
