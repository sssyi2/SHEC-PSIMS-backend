package com.webtab.shecpsims.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.model.entity.user.InviteUser;
import com.webtab.shecpsims.mapper.user.InviteUserMapper;
import com.webtab.shecpsims.service.user.InviteUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InviteUserServiceImpl extends ServiceImpl<InviteUserMapper, InviteUser>
        implements InviteUserService {

    @Override
    public String getInviteCode() {
            return UUID.randomUUID().toString().substring(0, 6); // 生成6位随机邀请码
    }

    @Override
    public InviteUser isUsedInviteCode(String inviteCode) {
        return baseMapper.selectOne(new QueryWrapper<InviteUser>().eq("invite_code",inviteCode));
    }
}
