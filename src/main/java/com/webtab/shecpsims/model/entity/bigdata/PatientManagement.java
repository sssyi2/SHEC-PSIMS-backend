package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("patient_management")
public class PatientManagement {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}