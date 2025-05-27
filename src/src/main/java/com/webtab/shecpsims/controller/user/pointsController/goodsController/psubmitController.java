package com.webtab.shecpsims.controller.user.pointsController.goodsController;

import com.webtab.shecpsims.model.entity.user.Goods;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.user.GoodsService;
import com.webtab.shecpsims.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/goods")
public class psubmitController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    @PostMapping("/exchange/{id}")
    public R exchangeGoods(@PathVariable("id")Integer goodsId,@RequestParam int UserID){
        if(goodsId == null){
            return R.error(503).msg("商品id不能为空");
        }
        if(UserID == 0){
            return R.error(504).msg("用户id不能为空");
        }
        Goods goods = goodsService.getGoodsById(goodsId);
        if(goods == null){
            return R.error(505).msg("商品不存在");
        }
        int goodsPoints = goods.getPoints();
        if(goodsPoints<0){
            return R.error(507).msg("积分数有误");
        }
        User user = userService.getUserById(UserID);
        if(user == null){
            return R.error(506).msg("用户不存在");
        }
        int userpoints = user.getUserpoints();
        if(userpoints<0){
            return R.error(507).msg("积分数有误");
        }
        boolean b = goodsService.exchangeGoods(goodsPoints, userpoints);
        if(b){
            if(user.getAddress()==null||user.getAddress().isEmpty()){
                return R.error(508).msg("地址不能为空，请先填写地址");
            }
            userpoints=userpoints-goodsPoints;
            user.setUserpoints(userpoints);
            userService.updateById(user);
          return R.ok().msg("兑换商品成功").data(user);
        }
       return R.error(532).msg("积分不足，无法兑换商品");

    }
}
