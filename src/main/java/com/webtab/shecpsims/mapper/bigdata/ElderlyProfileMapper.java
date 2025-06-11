package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.ElderlyProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.Map;

@Mapper
public interface ElderlyProfileMapper extends BaseMapper<ElderlyProfile> {

    /**
     * 统计当前在院患者数量
     */
    @Select("SELECT COUNT(*) FROM elderlyprofile  WHERE status = '住院中'")
    int countCurrentPatients();

    /**
     * 统计昨日在院患者数量 - 这里简化，实际应与数据库对应
     */
    @Select("SELECT COUNT(*) FROM elderlyprofile WHERE status = '住院中' " + "AND admission_date <= DATE_SUB(CURRENT_DATE(), INTERVAL 1 DAY)")
    int countYesterdayPatients();
}