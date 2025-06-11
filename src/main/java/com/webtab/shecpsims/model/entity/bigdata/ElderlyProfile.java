package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("elderlyprofile")
public class ElderlyProfile {

    @TableId(value = "patient_id", type = IdType.AUTO)
    private Integer patientId;

    private String name;
    private String gender;
    private Integer age;
    private Date birthDate;
    private String idNumber;
    private String contactInfo;
    private String address;
    private String familyMedicalHistory;
    private String allergyHistory;
    private String pastMedicalHistory;
    private Float height;
    private Float weight;
    private String bloodPressure;
    private Integer heartRate;
    private Date admissionDate;
    private Date dischargeDate;
    private String status;
    private Date createDate;
    private Date lastUpdate;
}