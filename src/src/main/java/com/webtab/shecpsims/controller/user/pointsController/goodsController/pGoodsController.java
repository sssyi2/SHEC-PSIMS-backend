package com.webtab.shecpsims.controller.user.pointsController.goodsController;

import com.webtab.shecpsims.model.entity.user.Goods;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.service.user.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient/goods")
public class pGoodsController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("/list")
    public R listGoods(){
        List<Goods> list = goodsService.list();
        return R.ok().data(list);

    }
}
