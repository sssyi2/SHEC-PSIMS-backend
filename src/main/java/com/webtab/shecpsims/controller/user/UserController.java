package com.webtab.shecpsims.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.webtab.shecpsims.model.entity.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ErrorCode;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.exception.BusinessException;
import com.webtab.shecpsims.exception.ThrowUtils;
import com.webtab.shecpsims.model.dto.user.UserLoginRequest;
import com.webtab.shecpsims.model.dto.user.UserRegisterRequest;
import com.webtab.shecpsims.model.dto.user.UserUpdateRequest;
import com.webtab.shecpsims.model.vo.user.LoginUserVO;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController{

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userLoginRequest
     * @return
     **/

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String username = userLoginRequest.getUserName();
        String password = userLoginRequest.getPassword();
        System.out.println(username+","+password);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userService.getOne(queryWrapper);
        String passwordHash = user.getPasswordHash();
        System.out.println(passwordHash);
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userService.userLogin(username, password, request);
        return ResultUtils.success(loginUserVO);
    }
    /**
     * 用户注册
     * @param userRegisterRequest 登录请求
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        String confirmPassword = userRegisterRequest.getConfirmPassword();
        if (StringUtils.isAnyBlank(username, password, confirmPassword)) {
            return null;
        }
        long result = userService.userRegister(username,password,confirmPassword);
        return ResultUtils.success(result);
    }

    /**
     * 修改个人信息 包括密码（所有东西）
     * @param userUpdateRequest 修改请求
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
                                            HttpServletRequest request) {
        // 1. 检查请求体是否为空
        if (userUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 2. 获取当前登录用户的 ID
        User loginUser = userService.getLoginUser(request);
        int userId = loginUser.getUserId();

        // 3. 创建 User 对象并赋值
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        user.setUserId(userId);

        // 4. 更新用户信息
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);

        // 5. 返回成功响应
        return ResultUtils.success(true);
    }

}


