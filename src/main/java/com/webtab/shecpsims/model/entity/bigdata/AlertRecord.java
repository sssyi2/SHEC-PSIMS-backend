package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("alert_record")
public class AlertRecord {

    @TableId(value = "warning_record_id", type = IdType.AUTO)
    private Integer warningRecordId;

    private Integer patientId;
    private Date date;
    private Date time;
    private String diceaseName;
    private String diceaseType;
    private Integer warningLevel;
    private String information;
}