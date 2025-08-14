package com.webtab.shecpsims.service.elderlyhealth.impl;

import com.webtab.shecpsims.mapper.elderlyhealth.PatientManagementMapper;
import com.webtab.shecpsims.model.entity.elderlyhealth.PatientManagement;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.PatientManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientManagementServiceImpl implements PatientManagementService {

    @Autowired
    private PatientManagementMapper patientManagementMapper;

    @Override
    public R save(PatientManagement patientManagement) {
        int insertResult = patientManagementMapper.insert(patientManagement);
        return insertResult > 0 ? R.ok().msg("创建成功") : R.error(500).msg("创建失败");
    }

    @Override
    public R deleteByDoctorIdAndPatientId(Integer doctorId, Integer patientId) {
        int deleteResult = patientManagementMapper.deleteByDoctorIdAndPatientId(doctorId, patientId);
        return deleteResult > 0 ? R.ok().msg("删除成功") : R.error(500).msg("删除失败");
    }

    @Override
    public PatientManagement findByDoctorIdAndPatientId(Integer doctorId, Integer patientId) {
        return patientManagementMapper.selectByDoctorIdAndPatientId(doctorId, patientId);
    }

    @Override
    public List<PatientManagement> findByDoctorId(Integer doctorId) {
        return patientManagementMapper.selectByDoctorId(doctorId);
    }

}