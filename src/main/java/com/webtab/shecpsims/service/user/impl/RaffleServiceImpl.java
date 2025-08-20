package com.webtab.shecpsims.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.model.entity.user.Raffle;
import com.webtab.shecpsims.mapper.user.RaffleMapper;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.user.PointsService;
import com.webtab.shecpsims.service.user.RaffleService;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RaffleServiceImpl extends ServiceImpl<RaffleMapper, Raffle>
        implements RaffleService {
    @Autowired
    private RaffleMapper raffleMapper;
    @Autowired
    private UserService userService;

    @Override
    public boolean addRaffle(Raffle raffle) {
        return save(raffle);
    }

    @Override//当对一个奖品进行修改时，应当设置其他奖品的花费积分与修改后的一致
    public boolean updateRaffle(List<Raffle> raffle) {
//        int roundId = raffle.getRoundId();
        for(Raffle r : raffle){
            r.setRoundId(r.getRoundId());
            r.setRaffleName(r.getRaffleName());
            r.setRoundName(r.getRoundName());
            r.setSpendPoints(r.getSpendPoints());
            r.setWinningProb(r.getWinningProb());
            r.setImage(r.getImage());
            int i = raffleMapper.updateById(r);
            if(i<0){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean deleteRaffle(int roundId) {
        QueryWrapper<Raffle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("round_id", roundId);
        int i = raffleMapper.delete(queryWrapper);
        return i>0;
    }

    @Override
    public int getSpendPointsById(int roundId) {
        QueryWrapper<Raffle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("round_id", roundId);
        List<Raffle> raffles = raffleMapper.selectList(queryWrapper);
        for(Raffle raffle1 : raffles){
            if(raffle1==null){
                return 0;
            }
            int spendPoints = raffle1.getSpendPoints();
            return spendPoints;
        }
        return 0;
    }

    @Override
    public List<Raffle> getRaffleById(int roundId) {
        List<Raffle> raffles = baseMapper.selectList(new QueryWrapper<Raffle>().eq("round_id", roundId));
        return raffles;
    }
//返回抽中的奖品id
    @Override
    public int getPrize(List<Raffle> raffles) {
        //给个初始值
        int random = 0;

        try {
            //计算权重和
            double sumWeight = 0;
            for (Raffle raffle1:raffles) {
                sumWeight += Double.valueOf(raffle1.getWinningProb());
            }

        //产生随机数
        double randomNumber;
        randomNumber = Math.random();

        //根据随机数在所有奖品分布的区域并确定所抽奖品
        double d1 = 0;
        double d2 = 0;
        for (int i = 0; i < raffles.size(); i++) {
            d2 += Double.parseDouble(String.valueOf(raffles.get(i).getWinningProb())) / sumWeight;
            if (i == 0) {
                d1 = 0;
            } else {
                d1 += Double.parseDouble(String.valueOf(raffles.get(i - 1).getWinningProb())) / sumWeight;
            }
            if (randomNumber >= d1 && randomNumber <= d2) {
                //返回抽中奖品的id
                random = raffles.get(i).getRaffleId();
                break;
            }
        }
    } catch (Exception e) {
        log.error("生成抽奖随机数出错，出错原因：" + e.getMessage());
    }
        return random;
}

    @Override
    public Raffle getPrizeById(int prizeId) {
        Raffle raffle = baseMapper.selectById(prizeId);
        return raffle;
    }

    @Override//得到奖品后的操作
    public boolean get(int UserID,Raffle prize) {
        User user = userService.getUserById(UserID);
        if(prize.getRaffleName().endsWith("积分")){
            // 正则匹配 "XX积分" 中的数字部分
            Pattern pattern = Pattern.compile("^(\\d+)积分$");
            Matcher matcher = pattern.matcher(prize.getRaffleName());

            if (matcher.find()) {
                int points = Integer.parseInt(matcher.group(1)); // 提取数字
                user.setUserpoints(user.getUserpoints() + points);
                userService.updateById(user);
                System.out.println("用户积分为："+user.getUserpoints());

                return true;


            }else{
                System.out.println("出错了");
                return false;
            }
        }else{
//            System.out.println(prize.getRaffleName());
            return true;
        }
    }

    @Override
    public int getMax() {
        LambdaQueryWrapper<Raffle> queryWrapper = new LambdaQueryWrapper<>();
        List<Raffle> raffles = raffleMapper.selectList(queryWrapper);
        int max = 0;
        for(Raffle raffle1 : raffles){
            if(max< raffle1.getRoundId()){
                max = raffle1.getRoundId();
            }
        }
        return max;
    }


}
