package com.webtab.shecpsims.controller.user.pointsController.goodsController.admin;

import jakarta.servlet.http.HttpServlet;
import com.webtab.shecpsims.model.entity.user.Goods;
import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.service.user.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author taojianbing
 * @version V1.0
 * Copyright (c) 2025, taojianbing@hwadee.com All Rights Reserved.
 * @ProjectName:Web04
 * @Title: GoodsEditController
 * @Package com.hwadee.controller.goods.admin
 * @Description: 修改商品信息
 * @date 2025/3/14 15:48
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsEditController extends HttpServlet {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/update/{goodsId}")
    public R updateGoods(@PathVariable("goodsId") Integer goodsId,
                         @RequestParam("goodsName") String name,
                         @RequestParam("goodsImage") MultipartFile image,
                         @RequestParam("points") String points) throws IOException {
        if (goodsId == null) {
            return R.error(503).msg("商品 ID 不能为空");
        }
       //判断id是否存在
        Goods originalGoods = goodsService.getGoodsById(goodsId);
        if (originalGoods == null) {
            return R.error(505).msg("该商品不存在");
        }
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setGoodsName(name);
        goods.setPoints(Integer.parseInt(points));
        if (image!=null||!image.isEmpty()) {
            ImgStr(image, goods);
        }else{
            goods.setGoodsImage(originalGoods.getGoodsImage());
        }

        if (goods.getGoodsName() == null || goods.getGoodsName().isEmpty()) {
                goods.setGoodsName(originalGoods.getGoodsName());
            }
        if (goods.getPoints() == 0) {
            goods.setPoints(originalGoods.getPoints());
        }
        if (goods.getGoodsImage() == null || goods.getGoodsImage().isEmpty()) {
            goods.setGoodsImage(originalGoods.getGoodsImage());
        }


        boolean b = goodsService.updateById(goods);
        return b ? R.ok().msg("修改成功") : R.error(505).msg("修改失败");

    }
       public void ImgStr(MultipartFile file, Goods goods)throws IOException {
           //我们简单验证一下file文件是否为空
           Date date = new Date();
           //获取当前系统时间年月这里获取到月如果要精确到日修改("yyyy-MM-dd")
           String dateForm = new SimpleDateFormat("yyyy-MM").format(date);
           //地址合并 path.getFileimg 是存放在实体类的路径 不会写得同学可以直接写 "D:\\img" 这文件要手动创建
           String casePath = "C:\\image"+dateForm;
           //获取图片后缀
           String imgFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
           //这里我们加入了验证图片类型 这需要自己手动写 声明点这只是非常简单的验证
           //删除不影响程序运行
           /***************************************************/
           ImgRegulation regulation = new ImgRegulation();
           try{ boolean ifimg = regulation.VERIFY(imgFormat);
               if (false==ifimg){ return; }
           }catch (Exception e){ return; }
           /****************************************************/
           //判断文件是否存在
           /*************************************************/
           File f = new File(casePath);
           try {if (!f.exists()){f.mkdirs();}
           }catch (Exception e){ return; }
           /*************************************************/
           //给图片重新随机生成名字
           String name= UUID.randomUUID().toString()+imgFormat;
           //保存图片
           file.transferTo(new File(casePath+"\\"+name));
           //拼接要保存在数据中的图片地址
           //path.getUREIMG() 同样也是存放在实体类的字段 可以直接写 http://localhost:8080/
           //dateForm 这是动态的文件夹所以要和地址一起存入数据库中
           //auser 为@RequestMapping("/auser")
           String urlImg ="http://localhost:8080/"+"admin/goods/static?fileUrl="+dateForm+"/"+name;
           //放入对应的字段中
           goods.setGoodsImage(urlImg);

       }

    }


