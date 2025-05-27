package com.webtab.shecpsims.controller.user.InformationController.PasswordController;

import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/modify")
public class PasswordController {
    @Autowired
    private UserService userService;
    @PostMapping("/{id}")

    public R modifyPassword(@PathVariable("id") int UserID, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword) {
        User user = userService.getUserById(UserID);
        if(user==null){
            return R.error(507).msg("用户不存在");
        }
        if(oldPassword==null||oldPassword.isEmpty()){
            return R.error(534).msg("旧密码不能为空");
        }
        if(newPassword==null||newPassword.isEmpty()){
            return R.error(535).msg("新密码不能为空");
        }
        if(confirmPassword==null||confirmPassword.isEmpty()){
            return R.error(536).msg("确认密码不能为空");
        }
        if(!oldPassword.equals(user.getPasswordHash())){
            return R.error(540).msg("密码错误");
        }
        if(oldPassword.equals(newPassword)){
            return R.error(537).msg("旧密码与新密码不能相同");
        }
        if(!newPassword.equals(confirmPassword)){
            return R.error(538).msg("新密码与确认密码不相同");
        }
        user.setPasswordHash(newPassword);
        boolean b = userService.updateById(user);
        return b?R.ok().data(user):R.error(539).msg("修改密码失败");

    }
}
