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
 * @Description: 添加商品接口
 * @date 2025/5/14 12:32
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsAddController extends HttpServlet {
    @Autowired
    private GoodsService goodsService;

    @PostMapping("/add")
    public R addGoods(@RequestParam("goodsName") String name,
                      @RequestParam("goodsImage") MultipartFile image,
                      @RequestParam("points") String points,
                      @RequestParam String des,
                      @RequestParam int number,
                      @RequestParam String status) throws IOException {
        try {//前端判断参数
            Goods goods = new Goods();
            goods.setGoodsName(name);//zhangsan
            goods.setPoints(Integer.parseInt(points));//20
            goods.setDes(des);
            System.out.println(points);
            goods.setStatus(status);
            goods.setNumber(number);
            ImgStr(image, goods);//设置图片
            System.out.println(goods);
            boolean result = goodsService.addGoods(goods);
            return result ? R.ok().msg("商品添加成功") : R.error(500).msg("商品添加失败");
        } catch (IOException e) {
            return R.error(501).msg("文件上传错误: " + e.getMessage());
        } catch (Exception e) {
            return R.error(502).msg("服务器内部错误: " + e.getMessage());
        }
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

