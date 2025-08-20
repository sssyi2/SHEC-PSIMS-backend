package com.webtab.shecpsims.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.webtab.shecpsims.model.entity.user.Goods;

import com.webtab.shecpsims.mapper.user.GoodsMapper;
import com.webtab.shecpsims.service.user.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
        implements GoodsService {

 @Autowired
 private RedisTemplate redisTemplate;

@Autowired
private GoodsMapper goodsMapper;
 @Override
 public boolean addGoods(Goods goods) {

  if (!StringUtils.hasLength(goods.getGoodsName())) {
   throw new IllegalArgumentException("商品名不能为空");
  } else if (!StringUtils.hasLength(String.valueOf(goods.getPoints()))) {
   throw new IllegalArgumentException("商品价格不能为空");
  } else {
   save(goods);
   return true;
  }


 }

 @Override
 public boolean deleteGoodsById(Integer goodsId) {
 int row=baseMapper.deleteById(goodsId);
 return row>0;
 }



 @Override
 public Goods getGoodsById(Integer goodsId) {
  return baseMapper.selectById(goodsId);
 }

 @Override
 public boolean exchangeGoods(int goodsPoints, int points) {
  if(points<goodsPoints){
   return false;
  }else {

   return true;
  }
 }

 @Override
 public List<Goods> getGoodsByName(String goodsName) {
  LambdaQueryWrapper<Goods> query=new LambdaQueryWrapper<>();
  query.likeRight(Goods::getGoodsName, goodsName); // 右匹配（如'abc%'）
  List<Goods> goods = goodsMapper.selectList(query);
  return goods;
 }

 @Override
 public List<Goods> listGoods() {
  LambdaQueryWrapper<Goods> goods=new LambdaQueryWrapper<>();
  goods.eq(Goods::getStatus,"已上架");
  goods.ge(Goods::getNumber,1);//展示库存大于等于一的商品
  List<Goods> goods1 = goodsMapper.selectList(goods);
  return goods1;
 }


}
