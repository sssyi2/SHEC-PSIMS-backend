package com.webtab.shecpsims.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;


/**
* 
* @TableName goods
*/

@ToString

public class Goods implements Serializable {

    /**
    * 
    */
    @TableId
    private Integer goodsId;
    /**
    * 
    */

    private String goodsName;
    /**
    * 
    */
//    @TableField("goodsImage")
    private String goodsImage;
    /**
    * 
    */
    private int points;
    private String des;
    @Enumerated(EnumType.STRING) // 确保按字符串存储
    @Column(columnDefinition = "ENUM('已上架','已下架')")
    private String status;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    /**
    * 
    */
    @Override
    public String toString() {
        return "Goods{" +
                "des='" + des + '\'' +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsImage='" + goodsImage + '\'' +
                ", points=" + points +
                ", status='" + status + '\'' +
                ", number=" + number +
                '}';
    }
}
