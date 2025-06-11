package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.FollowupPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FollowupPlanMapper extends BaseMapper<FollowupPlan> {

    /**
     * 统计随访计划总数
     */
    @Select("SELECT COUNT(*) FROM followupplan WHERE date <= CURDATE()")
    int countTotalFollowupPlans();
}