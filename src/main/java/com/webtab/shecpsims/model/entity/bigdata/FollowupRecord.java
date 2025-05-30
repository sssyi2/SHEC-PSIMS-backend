package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("followup_record")
public class FollowupRecord {

    @TableId(value = "visit_id", type = IdType.AUTO)
    private Integer visitId;

    private Integer visitPlanId;
    private Integer userId;
    private Integer patientId;
    private Date date;
    private Date time;
    private String visitRecord;
}