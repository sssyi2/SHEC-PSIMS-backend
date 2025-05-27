package com.webtab.shecpsims.service.bigdata;

import com.baomidou.mybatisplus.extension.service.IService;
import com.webtab.shecpsims.model.entity.bigdata.Elderlyprofile;

public interface ElderlyprofileService extends IService<Elderlyprofile> {

    /**
     * 查询患者总数
     */
    int getTotalPatientCount();
}
