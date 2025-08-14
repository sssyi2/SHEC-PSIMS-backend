package com.webtab.shecpsims.service.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.Prescription;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;

import java.util.List;

public interface PrescriptionService {
    R save(Prescription prescription);
    R update(Prescription prescription);
    R delete(Integer prescriptionId, Integer patientId);
    R delete(Integer patientId);
    List<Prescription> findByPatientId(Integer patientId);
    Prescription findByIdWithMessageStatus(Integer prescriptionId, Integer patientId);
    Prescription findByIdAndPatientId(Integer prescriptionId, Integer patientId);
}