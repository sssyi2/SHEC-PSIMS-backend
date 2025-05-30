package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("followupplan")
public class FollowupPlan {

    @TableId(value = "visit_plan_id", type = IdType.AUTO)
    private Integer visitPlanId;

    private Integer patientId;
    private Integer userId;
    private Date date;
    private Date time;
    private String note;
}