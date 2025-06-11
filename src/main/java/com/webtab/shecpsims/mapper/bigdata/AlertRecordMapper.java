package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.webtab.shecpsims.model.entity.bigdata.AlertRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AlertRecordMapper extends BaseMapper<AlertRecord> {

    /**
     * 统计当前预警数量
     */
    @Select("SELECT COUNT(*) FROM alert_record WHERE warning_level > 0")
    int countActiveAlerts();
}