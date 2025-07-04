package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.HealthRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Mapper
@Component("bigdataHealthRecordMapper")
public interface HealthRecordMapper extends BaseMapper<HealthRecord> {

    /**
     * 统计当前在院患者数量
     */
    @Select("SELECT COUNT(*) FROM health_record  WHERE status = '住院中'")
    int countCurrentPatients();

    /**
     * 统计昨日在院患者数量 - 这里简化，实际应与数据库对应
     */
    @Select("SELECT COUNT(*) FROM health_record WHERE status = '住院中' " + "AND admission_date <= DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY)")
    int countYesterdayPatients();

}