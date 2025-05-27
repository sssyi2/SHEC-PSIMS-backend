package com.webtab.shecpsims.controller.user.pointsController.goodsController.admin;
import jakarta.servlet.http.HttpServlet;
import com.webtab.shecpsims.model.entity.user.Goods;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.service.user.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: GoodsGetController
 * @Package com.hwadee.controller.goods.admin
 * @Description: 获取商品信息,查询
 * @date 2025/3/14 15:42
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsGetController extends HttpServlet {
    @Autowired
    private GoodsService goodsService;

   @GetMapping("/get/{id}")
    public R getGoods(@PathVariable("id")Integer goodsId) {

        if (goodsId == null) {
           return R.error(503).msg("商品id不能为空");
        } else {
            Goods goods = goodsService.getGoodsById(goodsId);
            if (goods == null) {
               return R.error(505).msg("商品不存在");
            } else {
                return R.ok().msg("查询成功").data(goods);
            }
        }
    }
}
