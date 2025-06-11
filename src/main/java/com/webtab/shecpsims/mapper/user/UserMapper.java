package com.webtab.shecpsims.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}