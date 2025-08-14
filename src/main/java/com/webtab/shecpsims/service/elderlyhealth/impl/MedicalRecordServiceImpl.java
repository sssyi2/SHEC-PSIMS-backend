package com.webtab.shecpsims.service.elderlyhealth.impl;

import com.webtab.shecpsims.mapper.elderlyhealth.MedicalRecordMapper;
import com.webtab.shecpsims.model.entity.elderlyhealth.MedicalRecord;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public R save(MedicalRecord medicalRecord) {
        if (medicalRecord.getPatientId() == null) {
            return R.error(500).msg("患者ID不能为空");
        }
        int insertResult = medicalRecordMapper.insert(medicalRecord);
        return insertResult > 0 ? R.ok() : R.error(500).msg("保存失败");
    }

    @Override
    public R update(MedicalRecord medicalRecord) {
        int updateResult = medicalRecordMapper.updateByIdAndPatientId(medicalRecord);
        return updateResult > 0 ? R.ok().msg("更新成功") : R.error(500).msg("更新失败");
    }

    @Override
    public R delete(Integer recordId, Integer patientId) {
        int deleteResult = medicalRecordMapper.deleteByIdAndPatientId(recordId, patientId);
        return deleteResult > 0 ? R.ok().msg("删除成功") : R.error(500).msg("删除失败");
    }

    @Override
    public R delete(Integer patientId) {
        int deleteResult = medicalRecordMapper.deletePatientAllMRecords(patientId);
        return deleteResult > 0 ? R.ok().msg("删除成功") : R.error(500).msg("删除失败");
    }

    @Override
    public List<MedicalRecord> findByPatientId(Integer patientId) {
        return medicalRecordMapper.selectByPatientId(patientId);
    }

}