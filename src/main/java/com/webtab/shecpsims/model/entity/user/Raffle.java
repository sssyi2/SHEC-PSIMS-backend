package com.webtab.shecpsims.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

//管理员设置，需要设置奖品，每个轮盘所需要的积分，每八个奖品组成一个轮盘
//抽奖实体，字段包含积分，奖品，
public class Raffle {
    @TableId(type= IdType.AUTO)
    private Integer raffleId;
    private String raffleName;
    private int spendPoints;
    private int roundId;
    //增加一个中奖概率
    private double winningProb;

    public String getRaffleName() {
        return raffleName;
    }

    public void setRaffleName(String raffleName) {
        this.raffleName = raffleName;
    }

    public Integer getRaffleId() {
        return raffleId;
    }

    public void setRaffleId(int raffleId) {
        this.raffleId = raffleId;
    }

    public int getSpendPoints() {
        return spendPoints;
    }

    public void setSpendPoints(int spendPoints) {
        this.spendPoints = spendPoints;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public double getWinningProb() {
        return winningProb;
    }

    public void setWinningProb(double winningProb) {
        this.winningProb = winningProb;
    }
}
