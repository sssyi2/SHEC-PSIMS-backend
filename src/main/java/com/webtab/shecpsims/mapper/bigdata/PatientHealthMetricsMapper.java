package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.PatientHealthMetrics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientHealthMetricsMapper extends BaseMapper<PatientHealthMetrics> {

    @Select("SELECT * FROM patient_health_metrics " +
            "WHERE patient_id = #{patientId} " +
            "AND record_date > DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
            "ORDER BY record_date ASC")
    List<PatientHealthMetrics> selectLast7DaysByPatientId(Integer patientId);
}