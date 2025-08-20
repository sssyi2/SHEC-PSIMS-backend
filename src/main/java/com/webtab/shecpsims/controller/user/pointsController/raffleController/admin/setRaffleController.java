package com.webtab.shecpsims.controller.user.pointsController.raffleController.admin;

import com.webtab.shecpsims.model.entity.user.R;
import com.webtab.shecpsims.model.entity.user.Raffle;
import com.webtab.shecpsims.service.user.RaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

//设置抽奖
@RestController
@RequestMapping("/admin/setRaffle")
public class setRaffleController {
    @Autowired
    private RaffleService raffleService;
    //添加奖品
    @PostMapping("/add/{id}")
    public R addRaffle(@RequestBody List<Raffle> list, @PathVariable("id") int roundId) {
        int spendPoints = list.get(0).getSpendPoints();
        for(Raffle raffle : list) {
            if(list.size()!=8){
                return R.error(523).msg("输入的奖品数必须等于八");
            }
            if(raffle.getRaffleName()==null){
                return R.error(524).msg("奖品名称不能为空");
            }
            if(raffle.getSpendPoints()==0){
                return R.error(525).msg("奖品的花费积分不能为空");
            }
            if(raffle.getSpendPoints()!=spendPoints){
                return R.error(526).msg("轮盘中奖品花费积分不一致");
            }
            raffle.setRoundId(roundId);
            boolean b = raffleService.addRaffle(raffle);
            if(!b){
                return R.error(527).msg("奖品添加失败");
            }
        }
        return R.ok().data(list);
    }
    //上传图片
    @PostMapping("/upload")
    public R uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "prizeIndex", required = false) Integer prizeIndex
    ) {
        final String UPLOAD_DIR = "src/main/resources/static/images/"; // 默认在项目根目录下
        try {
            // 1. 检查文件是否为空
            if (file.isEmpty()) {
                return R.error(571).msg("文件不能为空");
            }

            // 2. 检查文件类型（仅允许图片）
            String contentType = file.getContentType();
            if (!contentType.startsWith("image/")) {
                return R.error(572).msg("仅支持图片类型");
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

            // 如果需要关联奖品索引（prizeIndex），可以在这里处理
            if (prizeIndex != null) {
                // TODO: 将 fileUrl 保存到数据库，关联 prizeIndex
            }

            return R.ok().data("http://localhost:8080"+fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(573).msg("上传失败");
        }
    }
    //修改奖品
    @PostMapping("/update")
    public R updateRaffle(@RequestBody List<Raffle> raffles) {
        for(Raffle raffle : raffles){
            if(raffle.getRaffleName()==null){
                return R.error(524).msg("奖品名称不能为空");
            }
            if(raffle.getSpendPoints()==0){
                return R.error(525).msg("花费积分不能为空");
            }

        }
      //如何判断积分与轮盘中的花费积分相同
        //更新
        boolean b = raffleService.updateRaffle(raffles);
        return b?R.ok().data(raffles):R.error(529).msg("修改抽奖轮盘失败");
    }
    @DeleteMapping("/delete/{id}")
    public R deleteRaffle(@PathVariable("id")int roundId) {
        boolean b = raffleService.deleteRaffle(roundId);
        return b?R.ok().msg("删除成功"):R.error(530).msg("删除轮盘失败");
    }
    //显示指定抽奖轮盘
    @GetMapping("/list/{id}")
    public R listRaffle(@PathVariable("id") int roundId) {
        List<Raffle> raffle = raffleService.getRaffleById(roundId);

        return R.ok().data(raffle);
    }
    @GetMapping("/listAll")
    public R listAllRaffle() {
        List<Raffle> list = raffleService.list();
        return R.ok().data(list);
    }
    @GetMapping("/getMax")
    public R getMaxRoundId(){
        int max = raffleService.getMax();
        return R.ok().data(max);
    }
}
