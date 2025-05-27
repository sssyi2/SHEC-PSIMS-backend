package com.webtab.shecpsims.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webtab.shecpsims.model.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import com.webtab.shecpsims.model.vo.user.LoginUserVO;

/**
 * 用户服务
 */
public interface UserService extends IService<User>{
    /**
     * 用户注册
     *
     * @param username   用户账户
     * @param password  用户密码
     * @param confirmPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String username,String password,String confirmPassword);

    /**
     * 用户登录
     *
     * @param username 用户账户
     * @param  password 用户密码
     * @param request 用户信息
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String username,  String password, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 当前用户信息
     * @return 用户内容
     */
    User getLoginUser(HttpServletRequest request);

    public User getUserById(int UserID);

    User  getUserByUsername(String userName);

    User identityInvite(String inviteCode);


}