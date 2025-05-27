package com.webtab.shecpsims.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webtab.shecpsims.model.entity.user.Goods;

public interface GoodsService extends IService<Goods> {
    public boolean addGoods(Goods goods);

   public boolean deleteGoodsById(Integer goodsId);


   public Goods getGoodsById(Integer goodsId);

   public boolean exchangeGoods(int goodsPoints, int points);
}
