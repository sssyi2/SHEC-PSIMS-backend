package com.webtab.shecpsims.controller.user.pointsController.raffleController.admin;

import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.Raffle;
import com.webtab.shecpsims.service.user.RaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//设置抽奖
@RestController
@RequestMapping("/admin/setRaffle")
public class setRaffleController {
    @Autowired
    private RaffleService raffleService;
    //添加奖品
    @PostMapping("/add/{id}")
    public R addRaffle(@RequestBody List<Raffle> list, @PathVariable("id") int roundId) {
        int spendPoints = list.get(0).getSpendPoints();
        for(Raffle raffle : list) {
            if(list.size()!=8){
                return R.error(523).msg("输入的奖品数必须等于八");
            }
            if(raffle.getRaffleName()==null){
                return R.error(524).msg("奖品名称不能为空");
            }
            if(raffle.getSpendPoints()==0){
                return R.error(525).msg("奖品的花费积分不能为空");
            }
            if(raffle.getSpendPoints()!=spendPoints){
                return R.error(526).msg("轮盘中奖品花费积分不一致");
            }
            raffle.setRoundId(roundId);
            boolean b = raffleService.addRaffle(raffle);
            if(!b){
                return R.error(527).msg("奖品添加失败");
            }
        }
        return R.ok().data(list);
    }
    //修改奖品
    @PostMapping("/update/{id}")
    public R updateRaffle(@PathVariable("id")int raffleId,@RequestBody Raffle raffle) {
        if(raffleId==0){
            return R.error(528).msg("奖品ID不能为空");
        }
        if(raffle.getRaffleName()==null){
            return R.error(524).msg("奖品名称不能为空");
        }
        if(raffle.getSpendPoints()==0){
            return R.error(525).msg("花费积分不能为空");
        }//如何判断积分与轮盘中的花费积分相同
        boolean b = raffleService.updateRaffle(raffleId,raffle);
        return b?R.ok().data(raffle):R.error(529).msg("修改奖品失败");
    }
    @DeleteMapping("/delete/{id}")
    public R deleteRaffle(@PathVariable("id")int roundId) {
        boolean b = raffleService.deleteRaffle(roundId);
        return b?R.ok().msg("删除成功"):R.error(530).msg("删除轮盘失败");
    }
    //显示所有抽奖轮盘
    @GetMapping("/list")
    public R listRaffle(){
        raffleService.list();
        return R.ok().data(raffleService.list());
    }
}
