package com.webtab.shecpsims.controller.user.pointsController.inviteController;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.webtab.shecpsims.model.entity.user.InviteUser;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.user.InviteUserService;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//被邀请用户登录注册流程
@RestController
@RequestMapping("/user/Invited")
public class binviteController {
    @Autowired
    private UserService userService;
    @Autowired
    private InviteUserService inviteUserService;
    @PostMapping("/register")
    //生成邀请码邀请好友，好友登录注册时输入邀请码，邀请者获得积分
    public R inviteRegister(@RequestParam String UserName, @RequestParam String PasswordHash,@RequestParam String confirm,@RequestParam(required = false) String inviteCode) {
    if(UserName==null||UserName.isEmpty()){
    return R.error(518).msg("用户名不能为空");
    }
    if(PasswordHash==null||PasswordHash.isEmpty()){
        return R.error(519).msg("密码不能为空");
    }
    if(confirm==null||confirm.isEmpty()){
        return R.error(520).msg("确认密码不能为空");
    }
    //密码与确认密码验证
    User user = userService.getUserByUsername(UserName);
    if(user!=null){
            return R.error(521).msg("用户已存在");
    }
    //代表是普通注册用户
        User user1=new User();
    user1.setUserName(UserName);
    user1.setPasswordHash(PasswordHash);
    if(inviteCode==null||inviteCode.isEmpty()){
        userService.save(user1);
        return R.ok().msg("用户注册成功");
    }
    //否则是被邀请用户，需要输入邀请码
    User inviter = userService.identityInvite(inviteCode);
    if(inviter==null){
        return R.error(522).msg("邀请码不存在");
    }
        userService.save(user1);//在用户表中保存用户名和密码
    InviteUser inviteUser=new InviteUser();//在邀请表中保存用户名、密码、邀请码、关联用户ID
    inviteUser.setInviteCode(inviteCode);
    inviteUser.setUserName(UserName);
    inviteUser.setPassword(PasswordHash);
        int userID = inviter.getUserId();
    inviteUser.setConnectUserId(userID);
        inviteUserService.save(inviteUser);
//        return R.ok().data(inviteUser);

        if(userID==0){
            return R.error(503).msg("用户ID不能为空");
        }
        //判断邀请码是否被使用
        InviteUser inviteUser2 = inviteUserService.isUsedInviteCode(inviteCode);
        if(inviteUser2!=null){
            //邀请码已被使用

            inviter.setInviteSum(inviter.getInviteSum()+1);

            //增加积分
            int userpoints = inviter.getUserpoints();
            if(inviter.getInviteSum()==1){
                inviter.setUserpoints(userpoints+10) ;
            }else if(inviter.getInviteSum()==5){
                inviter.setUserpoints(userpoints+80) ;
            }else if(inviter.getInviteSum()==10){
                inviter.setUserpoints(userpoints+150) ;
            }else if(inviter.getInviteSum()==20){
                inviter.setUserpoints(userpoints+300) ;
            }else if(inviter.getInviteSum()==35){
                inviter.setUserpoints(userpoints+500) ;
            }else if(inviter.getInviteSum()==50){
                inviter.setUserpoints(userpoints+700) ;
            }
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("UserID",userID)
                    .set("inviteCode", null)
                    .set("inviteSum", inviter.getInviteSum())
                    .set("userpoints", inviter.getUserpoints());
            userService.update(null, updateWrapper);//保存修改后的用户
            return R.ok().msg("邀请好友成功");
        }
        //未使用积分不变
        return R.ok().msg("邀请码还未使用");
    }
}
