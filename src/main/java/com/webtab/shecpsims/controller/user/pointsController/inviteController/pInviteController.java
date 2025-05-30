package com.webtab.shecpsims.controller.user.pointsController.inviteController;

import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.user.InviteUserService;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//生成邀请码邀请好友
@RestController
@RequestMapping("/user/invite")
public class pInviteController {
    @Autowired
    private UserService userService;
    @Autowired
    private InviteUserService inviteUserService;
    @GetMapping("/getCode/{id}")
    public R getCode(@PathVariable("id") int UserId){
        String inviteCode=inviteUserService.getInviteCode();//生成邀请码
        User user = userService.getUserById(UserId);
        if(user==null){
            return R.error(506).msg("用户不存在");
        }

        user.setInviteCode(inviteCode);//把邀请码设置进用户中
        userService.updateById(user);
        return R.ok().data(inviteCode);
    }
//    @PostMapping("/check/{id}")
//    public R inviteUser(@PathVariable("id") int UserID){
//        if(UserID==0){
//            return R.error(503).msg("用户ID不能为空");
//        }
//        User user = userService.getUserById(UserID);
//        if(user==null){
//            return R.error(506).msg("用户不存在");
//        }
//        String inviteCode = user.getInviteCode();
//
//        //判断邀请码是否被使用
//        InviteUser inviteUser = inviteUserService.isUsedInviteCode(inviteCode);
//        if(inviteUser!=null){
//            //邀请码已被使用
//
//            user.setInviteSum(user.getInviteSum()+1);
//
//            //增加积分
//            int userpoints = user.getUserpoints();
//            if(user.getInviteSum()==1){
//                user.setUserpoints(userpoints+10) ;
//            }else if(user.getInviteSum()==5){
//                user.setUserpoints(userpoints+80) ;
//            }else if(user.getInviteSum()==10){
//                user.setUserpoints(userpoints+150) ;
//            }else if(user.getInviteSum()==20){
//                user.setUserpoints(userpoints+300) ;
//            }else if(user.getInviteSum()==35){
//                user.setUserpoints(userpoints+500) ;
//            }else if(user.getInviteSum()==50){
//                user.setUserpoints(userpoints+700) ;
//            }
//            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//            updateWrapper.eq("UserID", UserID)
//                    .set("inviteCode", null)
//                    .set("inviteSum", user.getInviteSum())
//                    .set("userpoints", user.getUserpoints());
//            userService.update(null, updateWrapper);//保存修改后的用户
//            return R.ok().msg("邀请好友成功");
//        }
//        //未使用积分不变
//        return R.ok().msg("邀请码还未使用");
//    }
}
