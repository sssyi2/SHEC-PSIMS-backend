package com.webtab.shecpsims.controller.user.pointsController.goodsController.admin;

import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.service.user.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author taojianbing
 * @version V1.0
 * Copyright (c) 2025, taojianbing@hwadee.com All Rights Reserved.
 * @ProjectName:Web04
 * @Title: GoodsDeleteController
 * @Package com.hwadee.controller.goods.admin
 * @Description: 删除商品数据
 * @date 2025/3/14 16:31
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsDeleteController {
    @Autowired
    private GoodsService goodsService;
    @DeleteMapping("/delete/{goodsId}")
    public R delGoods(@PathVariable("goodsId") Integer goodsId) throws IOException {
        if (goodsId == null) {
            return R.error(503).msg("id不能为空");
        } else {
            boolean flag = goodsService.deleteGoodsById(goodsId);
            if (flag) {
               return R.ok().msg("删除成功");
            } else {
                return R.error(504).msg("删除失败");
            }
        }
    }
}
