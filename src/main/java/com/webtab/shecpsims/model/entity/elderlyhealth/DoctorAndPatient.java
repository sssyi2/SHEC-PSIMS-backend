package com.webtab.shecpsims.model.entity.elderlyhealth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
//主管病人实体类
public class DoctorAndPatient implements Serializable {
    @TableId(value = "doctorPatientId", type = IdType.AUTO)
    private Integer doctorPatientId;
    private Integer doctorId;
    private Integer patientId;
    private String name;

}
