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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
                         @RequestParam("points") String points,
                         @RequestParam String des,
                         @RequestParam String status,
                         @RequestParam int number) throws IOException {
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
        goods.setNumber(number);
        goods.setStatus(status);
        goods.setDes(des);
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
    public void ImgStr(MultipartFile file, Goods goods) throws IOException {
        //我们简单验证一下file文件是否为空
        if (file == null || file.isEmpty()) {
            return;
        }
        final String UPLOAD_DIR = "src/main/resources/static/images/"; // 默认在项目根目录下
        try {
            // 2. 检查文件类型（仅允许图片）
            String contentType = file.getContentType();
            if (!contentType.startsWith("image/")) {
                return;
            }

            // 3. 生成唯一文件名（防止重名冲突）
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = System.currentTimeMillis() + fileExtension;

            // 4. 创建上传目录（如果不存在）
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 5. 保存文件到本地
            Path filePath = uploadPath.resolve(newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 6. 返回文件访问URL（需替换为你的实际域名或存储服务）
            String fileUrl = "/images/" + newFilename;

            //放入对应的字段中
            goods.setGoodsImage(fileUrl);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    }


