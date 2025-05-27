package com.webtab.shecpsims.controller.user.pointsController.raffleController;

import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.Raffle;
import com.webtab.shecpsims.model.entity.user.User;
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
        List<Raffle> raffles = raffleService.getRaffleById(roundId);
            int prizeId = raffleService.getPrize(raffles);//获得了奖品的id
            Raffle prize = raffleService.getPrizeById(prizeId);
            return R.ok().data(prize);

    }
}
