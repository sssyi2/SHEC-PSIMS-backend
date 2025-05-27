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
 * @Title: GoodsAddController
 * @Package com.hwadee.controller.goods.admin
 * @Description: 添加商品接口
 * @date 2025/3/14 12:32
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsAddController extends HttpServlet {
    @Autowired
    private GoodsService goodsService;
    @PostMapping("/add")
    public R addGoods(@RequestParam("goodsName") String name,
                      @RequestParam("goodsImage") MultipartFile image,
                      @RequestParam("points") String points) throws IOException {
try {
    Goods goods = new Goods();
    goods.setGoodsName(name);//zhangsan
    goods.setPoints(Integer.parseInt(points));//20
    ImgStr(image,goods);
    boolean result = goodsService.addGoods(goods);
    return result ? R.ok().msg("商品添加成功") : R.error(500).msg("商品添加失败");
} catch (IOException e) {
    return R.error(501).msg("文件上传错误: " + e.getMessage());
} catch (Exception e) {
    return R.error(502).msg("服务器内部错误: " + e.getMessage());
}
    }

    public void ImgStr( MultipartFile file, Goods goods)throws IOException {
        //我们简单验证一下file文件是否为空
        if (file.equals("")){return;}
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

