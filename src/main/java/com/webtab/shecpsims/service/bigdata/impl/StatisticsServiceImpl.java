package com.webtab.shecpsims.service.bigdata.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.webtab.shecpsims.mapper.bigdata.*;
import com.webtab.shecpsims.mapper.user.UserMapper;
import com.webtab.shecpsims.model.dto.bigdata.DashboardStatsDTO;
import com.webtab.shecpsims.model.dto.bigdata.DepartmentStatsDTO;
import com.webtab.shecpsims.model.dto.bigdata.StatisticsDTO;
import com.webtab.shecpsims.model.dto.bigdata.VisitStatsDTO;
import com.webtab.shecpsims.model.entity.bigdata.HealthRecord;
import com.webtab.shecpsims.model.entity.bigdata.PatientManagement;
import com.webtab.shecpsims.model.entity.user.User;
import com.webtab.shecpsims.service.bigdata.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private HealthRecordMapper healthRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PatientManagementMapper patientManagementMapper;

    @Override
    public StatisticsDTO getStatisticsData(String period) {
        log.info("获取统计数据，周期：{}", period);

        // 获取时间范围
        LocalDate endDate = LocalDate.now();
        LocalDate startDate;

        switch (period) {
            case "month":
                startDate = endDate.minusMonths(1);
                break;
            case "year":
                startDate = endDate.minusYears(1);
                break;
            case "week":
            default:
                startDate = endDate.minusWeeks(1);
                period = "week"; // 设置默认值
                break;
        }

        // 1. 获取每日就诊数据
        List<VisitStatsDTO> dailyVisits = getDailyVisits(startDate, endDate, period);

        // 2. 获取科室分布数据
        List<DepartmentStatsDTO> departmentStats = getDepartmentStats();

        // 3. 获取仪表盘统计数据
        DashboardStatsDTO stats = getDashboardStats(startDate, endDate);

        return new StatisticsDTO(dailyVisits, departmentStats, stats);
    }

    /**
     * 获取每日就诊数据
     */
    private List<VisitStatsDTO> getDailyVisits(LocalDate startDate, LocalDate endDate, String period) {
        try {
            // 不同周期的数据格式不同
            String dateFormat;
            String groupField;

            switch (period) {
                case "month":
                    dateFormat = "%Y-%m-%d";
                    groupField = "DATE_FORMAT(admission_date, '%Y-%m-%d')";
                    break;
                case "year":
                    dateFormat = "%Y-%m";
                    groupField = "DATE_FORMAT(admission_date, '%Y-%m')";
                    break;
                case "week":
                default:
                    dateFormat = "%W";
                    groupField = "DATE_FORMAT(admission_date, '%W')";
                    break;
            }

            // 使用 QueryWrapper 构建查询
            QueryWrapper<HealthRecord> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("DATE_FORMAT(admission_date, '" + dateFormat + "') as day", "COUNT(*) as count")
                    .apply("admission_date BETWEEN {0} AND {1}", startDate, endDate)
                    .apply("EXISTS (SELECT 1 FROM user u " +
                            "JOIN user_role ur ON u.user_id = ur.user_id " +
                            "JOIN role r ON ur.role_id = r.role_id " +
                            "WHERE u.user_id = health_record.patient_id " +
                            "AND (r.role_name = 'patient'))")
                    .groupBy(groupField);

            // 排序规则
            if ("week".equals(period)) {
                queryWrapper.last("ORDER BY FIELD(day, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')");
            } else {
                queryWrapper.orderByAsc("day");
            }

            // 执行查询
            List<Map<String, Object>> results = healthRecordMapper.selectMaps(queryWrapper);

            // 转换结果
            List<VisitStatsDTO> visits = new ArrayList<>();

            if ("week".equals(period)) {
                // 对于周视图，需要转换英文星期为中文
                Map<String, String> dayMap = new HashMap<>();
                dayMap.put("Monday", "周一");
                dayMap.put("Tuesday", "周二");
                dayMap.put("Wednesday", "周三");
                dayMap.put("Thursday", "周四");
                dayMap.put("Friday", "周五");
                dayMap.put("Saturday", "周六");
                dayMap.put("Sunday", "周日");

                // 先填充所有星期几，确保数据完整
                Map<String, Integer> dayCountMap = new HashMap<>();
                for (Map<String, Object> row : results) {
                    String englishDay = row.get("day").toString();
                    Integer count = Integer.valueOf(row.get("count").toString());
                    dayCountMap.put(englishDay, count);
                }

                // 按顺序添加到结果集，不存在的日期填充0
                for (String englishDay : Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")) {
                    String chineseDay = dayMap.get(englishDay);
                    Integer count = dayCountMap.getOrDefault(englishDay, 0);
                    visits.add(new VisitStatsDTO(chineseDay, count));
                }
            } else {
                // 对于月度和年度视图，直接使用数据库返回的格式
                for (Map<String, Object> row : results) {
                    String day = row.get("day").toString();
                    Integer count = Integer.valueOf(row.get("count").toString());

                    // 年度视图转换为"X月"格式
                    if ("year".equals(period) && day.contains("-")) {
                        String month = day.split("-")[1];
                        day = Integer.parseInt(month) + "月";
                    }

                    visits.add(new VisitStatsDTO(day, count));
                }
            }

            return visits;
        } catch (Exception e) {
            log.error("获取每日就诊数据失败", e);
            // 返回空列表而不是抛出异常，允许其他数据正常显示
            return new ArrayList<>();
        }
    }

    /**
     * 获取科室分布数据
     */
    private List<DepartmentStatsDTO> getDepartmentStats() {
        try {
            // 构建科室分布查询
            // 为了使用子查询，这里直接构造SQL，不用MybatisPlus链式API
            List<Map<String, Object>> results = patientManagementMapper.selectDepartmentStats();

            // 转换结果
            List<DepartmentStatsDTO> departments = new ArrayList<>();
            for (Map<String, Object> row : results) {
                String name = row.get("name").toString();
                Integer value = Integer.valueOf(row.get("value").toString());
                departments.add(new DepartmentStatsDTO(name, value));
            }

            return departments;
        } catch (Exception e) {
            log.error("获取科室分布数据失败", e);
            return new ArrayList<>();
        }
    }

    /**
     * 获取仪表盘统计数据
     */
    private DashboardStatsDTO getDashboardStats(LocalDate startDate, LocalDate endDate) {
        try {
            // 1. 总患者数
            QueryWrapper<User> patientQueryWrapper = new QueryWrapper<>();
            patientQueryWrapper.apply("EXISTS (SELECT 1 FROM user_role ur " +
                    "JOIN role r ON ur.role_id = r.role_id " +
                    "WHERE ur.user_id = user.user_id " +
                    "AND (r.role_name = '老人/患者' OR r.role_name LIKE '%患者%'))");
            Integer totalPatients = userMapper.selectCount(patientQueryWrapper).intValue();

            // 2. 新增患者数
            QueryWrapper<HealthRecord> newPatientQueryWrapper = new QueryWrapper<>();
            newPatientQueryWrapper.between("admission_date", startDate, endDate)
                    .apply("EXISTS (SELECT 1 FROM user u " +
                            "JOIN user_role ur ON u.user_id = ur.user_id " +
                            "JOIN role r ON ur.role_id = r.role_id " +
                            "WHERE u.user_id = health_record.patient_id " +
                            "AND (r.role_name = 'patient'))");
            Integer newPatients = healthRecordMapper.selectCount(newPatientQueryWrapper).intValue();

            // 3. 总医生数
            QueryWrapper<User> doctorQueryWrapper = new QueryWrapper<>();
            doctorQueryWrapper.apply("EXISTS (SELECT 1 FROM user_role ur " +
                    "JOIN role r ON ur.role_id = r.role_id " +
                    "WHERE ur.user_id = user.user_id " +
                    "AND r.role_name = 'doctor')");
            Integer totalDoctors = userMapper.selectCount(doctorQueryWrapper).intValue();

            // 4. 平均每日就诊人数（用 QueryWrapper 统计每日，再 Java 计算平均）
            QueryWrapper<HealthRecord> avgWrapper = new QueryWrapper<>();
            avgWrapper.select("DATE(admission_date) as visit_date", "COUNT(DISTINCT patient_id) as daily_count")
                    .between("admission_date", startDate, endDate)
                    .apply("EXISTS (SELECT 1 FROM user u " +
                            "JOIN user_role ur ON u.user_id = ur.user_id " +
                            "JOIN role r ON ur.role_id = r.role_id " +
                            "WHERE u.user_id = health_record.patient_id " +
                            "AND (r.role_name = 'patient'))")
                    .groupBy("DATE(admission_date)");
            List<Map<String, Object>> dailyStats = healthRecordMapper.selectMaps(avgWrapper);

            int sum = 0;
            for (Map<String, Object> row : dailyStats) {
                sum += Integer.parseInt(row.get("daily_count").toString());
            }
            int avgVisits = dailyStats.size() > 0 ? Math.round((float) sum / dailyStats.size()) : 0;

            return new DashboardStatsDTO(totalPatients, newPatients, totalDoctors, avgVisits);
        } catch (Exception e) {
            log.error("获取仪表盘统计数据失败", e);
            return new DashboardStatsDTO(0, 0, 0, 0);
        }
    }
}