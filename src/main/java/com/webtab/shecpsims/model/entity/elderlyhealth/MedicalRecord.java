package com.webtab.shecpsims.model.entity.elderlyhealth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@ToString
//电子病历实体类
public class MedicalRecord implements Serializable {

    @TableId(value = "recordId", type = IdType.AUTO)
    private Integer recordId;
    private Integer patientId;
    private String name;
    private String gender;
    private Integer age;
    private String diagnosis;
    private String treatmentPlan;
    private Date createTime;
    private Date updateTime;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
