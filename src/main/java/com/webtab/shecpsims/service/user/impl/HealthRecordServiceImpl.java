package com.webtab.shecpsims.service.user.impl;

import com.webtab.shecpsims.model.entity.elderlyhealth.HealthRecord;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.mapper.elderlyhealth.HealthRecordMapper;
import com.webtab.shecpsims.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordMapper healthRecordMapper;

    @Override
    public R save(HealthRecord healthRecord) {
        int insertResult = healthRecordMapper.insert(healthRecord);
        return insertResult > 0 ? R.ok().msg("创建成功") : R.error(500).msg("创建失败");
    }

    @Override
    public R update(HealthRecord healthRecord) {
        int updateResult = healthRecordMapper.updateByIdAndPatientId(healthRecord);
        return updateResult > 0 ? R.ok().msg("更新成功") : R.error(500).msg("更新失败");
    }

    @Override
    public R delete(Integer healthRecordId, Integer patientId) {
        int deleteResult = healthRecordMapper.deleteByIdAndPatientId(healthRecordId, patientId);
        return deleteResult > 0 ? R.ok().msg("删除成功") : R.error(500).msg("删除失败");
    }

    @Override
    public List<HealthRecord> findByPatientId(Integer patientId) {
        return healthRecordMapper.selectByPatientId(patientId);
    }

    @Override
    public List<HealthRecord> findAll(int page, int limit) {
        return healthRecordMapper.selectAll(page, limit);
    }
}