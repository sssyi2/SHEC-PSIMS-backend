package com.webtab.shecpsims.controller.user.pointsController.goodsController.admin;

import jakarta.servlet.http.HttpServlet;

import com.webtab.shecpsims.model.entity.user.Goods;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.service.user.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: GoodsListController
 * @Package com.hwadee.controller.goods.admin
 * @Description: 商品列表
 * @date 2025/3/14 11:34
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsListController extends HttpServlet {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    public R listGoods() {
        List<Goods> list = goodsService.list();
        return R.ok().data(list);
    }

}
