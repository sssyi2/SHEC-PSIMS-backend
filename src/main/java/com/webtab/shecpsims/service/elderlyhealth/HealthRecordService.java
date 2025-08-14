package com.webtab.shecpsims.service.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.HealthRecord;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;

import java.util.List;

public interface HealthRecordService {
    R save(HealthRecord healthRecord);
    R update(HealthRecord healthRecord);
    R delete(Integer healthRecordId, Integer patientId);
    R delete(Integer patientId);
    List<HealthRecord> findByPatientId(Integer patientId);
}