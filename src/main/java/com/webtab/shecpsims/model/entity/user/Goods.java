package com.webtab.shecpsims.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.ToString;

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
    @TableField("goodsName")
    private String goodsName;
    /**
    * 
    */
    @TableField("goodsImage")
    private String goodsImage;
    /**
    * 
    */
    private int points;

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

}
