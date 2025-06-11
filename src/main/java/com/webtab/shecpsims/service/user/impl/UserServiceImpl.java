package com.webtab.shecpsims.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.common.ErrorCode;
import com.webtab.shecpsims.exception.BusinessException;
import com.webtab.shecpsims.mapper.user.UserMapper;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.model.vo.user.LoginUserVO;
import com.webtab.shecpsims.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public long userRegister(String username, String password, String confirmPassword) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        long count = this.baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }

        User user = new User();
        user.setUserName(username);
        user.setPasswordHash(password);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }
        return user.getUserId();
    }


    @Override
    public LoginUserVO userLogin(String username, String password, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(username,password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (username.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 2.查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", password);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
     // 从 session 中获取登录用户ID
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        String loginUserId = (String) session.getAttribute("loginUserId");
        if (loginUserId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        User currentUser = this.getById(loginUserId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public User getUserById(int UserID) {
        return baseMapper.selectById(UserID);
    }

    @Override
    public User getUserByUsername(String UserName) {
       return baseMapper.selectOne(new QueryWrapper<User>().eq("user_name", UserName));
    }

    @Override
    public User identityInvite(String inviteCode) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("invite_code",inviteCode));
    }
}
