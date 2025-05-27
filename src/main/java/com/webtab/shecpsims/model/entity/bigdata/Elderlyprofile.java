package com.webtab.shecpsims.model.entity.bigdata;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * (Elderlyprofile)实体类
 *
 * @author makejava
 * @since 2025-05-16 14:18:43
 */
@Data
@ToString
public class Elderlyprofile implements Serializable {
    private static final long serialVersionUID = 989085108364502538L;

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


}

