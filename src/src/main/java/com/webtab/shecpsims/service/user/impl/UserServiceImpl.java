package com.webtab.shecpsims.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.mapper.user.UserMapper;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Override
    public User getUserById(int UserID) {
       return baseMapper.selectById(UserID);
    }

    @Override
    public User getUserByUsername(String UserName) {
       return baseMapper.selectOne(new QueryWrapper<User>().eq("UserName", UserName));
    }

    @Override
    public User identityInvite(String inviteCode) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("inviteCode",inviteCode));
    }
}
