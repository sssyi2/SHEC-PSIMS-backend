package com.webtab.shecpsims.service.bigdata.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webtab.shecpsims.mapper.bigdata.ElderlyprofileMapper;
import com.webtab.shecpsims.model.entity.bigdata.Elderlyprofile;
import com.webtab.shecpsims.service.bigdata.ElderlyprofileService;
import org.springframework.stereotype.Service;

@Service
public class ElderlyprofileServiceImpl extends ServiceImpl<ElderlyprofileMapper, Elderlyprofile> implements ElderlyprofileService {

    @Override
    public int getTotalPatientCount() {
        return baseMapper.countAllPatients();
    }
}
