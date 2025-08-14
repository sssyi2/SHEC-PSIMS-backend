package com.webtab.shecpsims.service.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.MedicalRecord;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;

import java.util.List;

public interface MedicalRecordService {
    R save(MedicalRecord medicalRecord);
    R update(MedicalRecord medicalRecord);
    R delete(Integer recordId, Integer patientId);
    R delete(Integer patientId);
    List<MedicalRecord> findByPatientId(Integer patientId);
}