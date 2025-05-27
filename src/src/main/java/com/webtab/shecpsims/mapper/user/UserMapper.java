package com.webtab.shecpsims.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.webtab.shecpsims.model.entity.user.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
