package com.webtab.shecpsims.mapper.bigdata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webtab.shecpsims.model.entity.bigdata.Elderlyprofile;
import org.apache.ibatis.annotations.Select;

public interface ElderlyprofileMapper extends BaseMapper<Elderlyprofile> {

    /**
     * 查询所有患者的数量
     */
    @Select("SELECT COUNT(*) FROM elderlyprofile")
    int countAllPatients();
}
