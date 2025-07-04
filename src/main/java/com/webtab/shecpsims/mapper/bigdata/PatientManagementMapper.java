package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.PatientManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PatientManagementMapper extends BaseMapper<PatientManagement> {
    @Select("SELECT u.department AS name, COUNT(DISTINCT pm.patient_id) AS value " +
            "FROM patient_management pm " +
            "JOIN user u ON pm.doctor_id = u.user_id " +
            "WHERE u.department IS NOT NULL AND u.department != '' " +
            "GROUP BY u.department " +
            "ORDER BY value DESC " +
            "LIMIT 5")
    List<Map<String, Object>> selectDepartmentStats();
}