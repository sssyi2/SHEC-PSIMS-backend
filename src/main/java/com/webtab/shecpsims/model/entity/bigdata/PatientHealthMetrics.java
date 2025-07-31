package com.webtab.shecpsims.model.entity.bigdata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("patient_health_metrics")
public class PatientHealthMetrics {
    private Integer id;
    private Integer patientId;
    private Date recordDate;
    private Integer age;
    private String gender;
    private String diseaseName;
    private Double systolicPressure;
    private Double diastolicPressure;
    private Double bloodSugar;
    private Double bmi;
    private String otherMetrics;
    private String dataSource;
    private Date createdAt;
    private Date updatedAt;
}