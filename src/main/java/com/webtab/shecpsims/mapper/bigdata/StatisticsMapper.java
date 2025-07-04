package com.webtab.shecpsims.mapper.bigdata;

import com.webtab.shecpsims.model.dto.bigdata.DepartmentStatsDTO;
import com.webtab.shecpsims.model.dto.bigdata.VisitStatsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatisticsMapper {

    /**
     * 获取指定时间范围内的每日就诊数据，只统计患者角色的用户
     */
    @Select("SELECT DATE_FORMAT(hr.admission_date, '%W') as day, COUNT(*) as count " +
            "FROM health_record hr " +
            "JOIN user u ON hr.patient_id = u.user_id " +
            "JOIN user_role ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "WHERE hr.admission_date BETWEEN #{startDate} AND #{endDate} " +
            "AND r.role_name = 'patient' " +
            "GROUP BY DATE_FORMAT(hr.admission_date, '%W') " +
            "ORDER BY FIELD(day, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')")
    List<VisitStatsDTO> getVisitsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取不同科室的患者数量
     */
    @Select("SELECT u.department as name, COUNT(DISTINCT pm.patient_id) as value " +
            "FROM patient_management pm " +
            "JOIN user u ON pm.doctor_id = u.user_id " +
            "WHERE u.department IS NOT NULL AND u.department != '' " +
            "GROUP BY u.department " +
            "ORDER BY value DESC " +
            "LIMIT 5")
    List<DepartmentStatsDTO> getDepartmentStats();

    /**
     * 获取总患者数（根据角色）
     */
    @Select("SELECT COUNT(DISTINCT u.user_id) " +
            "FROM user u " +
            "JOIN user_role ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "WHERE r.role_name = 'patient'")
    Integer getTotalPatients();

    /**
     * 获取指定时间范围内的新增患者数
     */
    @Select("SELECT COUNT(DISTINCT hr.patient_id) " +
            "FROM health_record hr " +
            "JOIN user u ON hr.patient_id = u.user_id " +
            "JOIN user_role ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "WHERE hr.admission_date BETWEEN #{startDate} AND #{endDate} " +
            "AND r.role_name = 'patient'")
    Integer getNewPatients(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取总医生数（根据角色）
     */
    @Select("SELECT COUNT(DISTINCT u.user_id) " +
            "FROM user u " +
            "JOIN user_role ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "WHERE r.role_name = 'doctor'")
    Integer getTotalDoctors();

    /**
     * 获取指定时间范围内的平均每日就诊人数
     */
    @Select("SELECT IFNULL(ROUND(AVG(daily_count), 0), 0) " +
            "FROM (" +
            "  SELECT COUNT(DISTINCT hr.patient_id) as daily_count, DATE(hr.admission_date) as visit_date " +
            "  FROM health_record hr " +
            "  JOIN user u ON hr.patient_id = u.user_id " +
            "  JOIN user_role ur ON u.user_id = ur.user_id " +
            "  JOIN role r ON ur.role_id = r.role_id " +
            "  WHERE hr.admission_date BETWEEN #{startDate} AND #{endDate} " +
            "  AND r.role_name = 'patient' " +
            "  GROUP BY DATE(hr.admission_date)" +
            ") as daily_stats")
    Integer getAverageVisits(@Param("startDate") String startDate, @Param("endDate") String endDate);
}