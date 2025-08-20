package com.webtab.shecpsims.controller.user.pointsController.goodsController.admin;

import jakarta.servlet.http.HttpServlet;

import com.webtab.shecpsims.model.entity.user.Goods;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.service.user.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/select/goods")
    public R selectGoodsByName(@RequestParam String goodsName) {
        List<Goods> goodsByName = goodsService.getGoodsByName(goodsName);
        return R.ok().data(goodsByName);
    }
    @PostMapping("/updateStatus")
    public R updateStatus(@RequestParam int goodsId, @RequestParam String status) {
        if(goodsId==0){
            return R.error(524).msg("商品ID不能为零");
        }
        Goods goodsById = goodsService.getGoodsById(goodsId);
        if(goodsById==null){
            return R.error(525).msg("商品不存在");
        }
        if(status==null||status.isEmpty()){
            return R.error(526).msg("商品状态不正确");
        }
        goodsById.setStatus(status);
        boolean b = goodsService.updateById(goodsById);
        return b?R.ok():R.error(527).msg("更新状态失败");
    }

}
