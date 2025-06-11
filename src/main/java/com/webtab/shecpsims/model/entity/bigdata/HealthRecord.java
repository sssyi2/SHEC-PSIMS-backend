package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("health_record")
public class HealthRecord {

    @TableId(value = "health_record_id", type = IdType.AUTO)
    private Integer healthRecordId;
    private Integer patientId;

    private String name;
    private String gender;
    private Integer age;
    private Date birthDate;
    private String idNumber;
    private String contactNumber;
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