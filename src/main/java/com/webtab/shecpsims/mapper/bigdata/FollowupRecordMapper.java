package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.FollowupRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FollowupRecordMapper extends BaseMapper<FollowupRecord> {

    /**
     * 计算已完成的随访记录数
     */
    @Select("SELECT COUNT(*) FROM followup_record WHERE date <= CURDATE()")
    int countCompletedFollowups();

    /**
     * 计算随访完成率
     */
    @Select("SELECT " +
            "  CASE " +
            "    WHEN COUNT(fp.visit_plan_id) = 0 THEN 0 " +
            "    ELSE ROUND(SUM(CASE WHEN fr.visit_id IS NOT NULL THEN 1 ELSE 0 END) * 100.0 / COUNT(fp.visit_plan_id), 0) " +
            "  END " +
            "FROM followupplan fp " +
            "LEFT JOIN followup_record fr ON fp.visit_plan_id = fr.visit_plan_id " +
            "WHERE fp.date <= CURDATE()")
    double getFollowupCompletionRate();
}