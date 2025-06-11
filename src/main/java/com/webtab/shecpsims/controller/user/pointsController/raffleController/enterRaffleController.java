package com.webtab.shecpsims.controller.user.pointsController.raffleController;

import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.Raffle;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.user.PointsService;
import com.webtab.shecpsims.service.user.RaffleService;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//参与抽奖,点击抽奖减少积分显示奖品，点击确认按钮收获奖品
@RestController
@RequestMapping("/user/raffle")
public class enterRaffleController {
    @Autowired
    private RaffleService raffleService;
    @Autowired
    private UserService userService;
    @Autowired
    private PointsService pointsService;

    @GetMapping("/get/{id}")
    public R enterRaffle(@PathVariable("id")int UserID, @RequestParam int roundId){
        User user = userService.getUserById(UserID);
        if(user == null){
            return R.error(506).msg("用户不存在");
        }
        //比较积分
        int userpoints = user.getUserpoints();
        if(userpoints < 0){
            return R.error(507).msg("积分数有误");
        }
        //判断roundId是否有效
        int spendPoints = raffleService.getSpendPointsById(roundId);
        if(spendPoints==0){
            return R.error(531).msg("找不到该轮盘");
        }
        //如果有效
        if(userpoints<spendPoints){
            //积分不足
            return R.error(532).msg("用户积分不足");
        }
        user.setUserpoints(user.getUserpoints() - spendPoints);
         userService.updateById(user);
        pointsService.updateUserPoints2(user.getUserpoints());//积分改变发送消息
        List<Raffle> raffles = raffleService.getRaffleById(roundId);
        System.out.println("奖品轮盘已找到");
            int prizeId = raffleService.getPrize(raffles);//获得了奖品的id
        System.out.println("奖品id已经找到");
            Raffle prize = raffleService.getPrizeById(prizeId);//获得奖品
        System.out.println("奖品已找到");
        boolean flag = raffleService.get(UserID, prize);
        if(flag){
            user = userService.getById(UserID); // 重新获取最新状态
//            pointsService.updateUserPoints2(user.getUserpoints());
            return R.ok().data(prize);
        }
       return R.error(555).msg("抽奖出现错误");

    }
    @PostMapping("/setAddress/{id}")
    public R setAdd(@PathVariable("id") int UserID, @RequestParam String address,@RequestParam(required = false)String phoneNumber){
     if(address==null || address.isEmpty()){
    return R.error(550).msg("地址不能为空");
        }
     User user = userService.getUserById(UserID);
        if(user == null){
            return R.error(506).msg("用户不存在");
        }//用户存在的情况下判断是否填入了手机号
     if(phoneNumber==null || phoneNumber.isEmpty()){//不填入手机号
         System.out.println("未填入手机号");
         user.setAddress(address);
         boolean b = userService.updateById(user);
         return b?R.ok().data(user):R.error(549).msg("设置收货地址失败");
     }else{
         System.out.println("已输入手机号");
         user.setAddress(address);
         user.setPhoneNumber(phoneNumber);
         boolean b = userService.updateById(user);
         return b?R.ok().data(user):R.error(549).msg("设置收货地址与手机号码失败");
     }
    }
}
