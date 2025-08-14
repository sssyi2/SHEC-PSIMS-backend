package com.webtab.shecpsims.service.elderlyhealth.impl;

import com.webtab.shecpsims.mapper.elderlyhealth.PrescriptionMapper;
import com.webtab.shecpsims.model.entity.elderlyhealth.Prescription;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @Override
    public R save(Prescription prescription) {
        int insertResult = prescriptionMapper.insert(prescription);
        return insertResult > 0 ? R.ok().msg("创建成功") : R.error(500).msg("创建失败");
    }

    @Override
    public R update(Prescription prescription) {
        int updateResult = prescriptionMapper.updateByIdAndPatientId(prescription);
        return updateResult > 0 ? R.ok().msg("更新成功") : R.error(500).msg("更新失败");
    }

    @Override
    public R delete(Integer prescriptionId, Integer patientId) {
        int deleteResult = prescriptionMapper.deleteByIdAndPatientId(prescriptionId, patientId);
        return deleteResult > 0 ? R.ok().msg("删除成功") : R.error(500).msg("删除失败");
    }

    @Override
    public R delete(Integer patientId) {
        int deleteResult = prescriptionMapper.deletePatientAllPrescription(patientId);
        return deleteResult > 0 ? R.ok().msg("删除成功") : R.error(500).msg("删除失败");
    }

    @Override
    public List<Prescription> findByPatientId(Integer patientId) {
        return prescriptionMapper.selectByPatientId(patientId);
    }

    @Override
    public Prescription findByIdWithMessageStatus(Integer prescriptionId, Integer patientId) {
        return prescriptionMapper.findByIdWithMessageStatus(prescriptionId, patientId);
    }

    @Override
    public Prescription findByIdAndPatientId(Integer prescriptionId, Integer patientId) {
        return prescriptionMapper.findByIdAndPatientId(prescriptionId, patientId);
    }
}