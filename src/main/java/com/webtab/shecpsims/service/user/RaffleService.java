package com.webtab.shecpsims.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webtab.shecpsims.model.entity.user.Raffle;

import java.util.List;

public interface RaffleService extends IService<Raffle> {
    boolean addRaffle(Raffle raffle);

    boolean updateRaffle(int raffleId,Raffle raffle);

    boolean  deleteRaffle(int roundId);

    int getSpendPointsById(int roundId);

    List<Raffle> getRaffleById(int roundId);

    int getPrize(List<Raffle> raffles);

    Raffle getPrizeById(int prizeId);

    boolean get(int UserID,Raffle prize);
}
