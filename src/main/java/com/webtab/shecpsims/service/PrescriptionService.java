package com.webtab.shecpsims.service;

import com.webtab.shecpsims.model.entity.elderlyhealth.Prescription;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import java.util.List;

public interface PrescriptionService {
    R save(Prescription prescription);
    R update(Prescription prescription);
    R delete(Integer prescriptionId, Integer patientId);
    List<Prescription> findByPatientId(Integer patientId);
    List<Prescription> findAll(int page, int limit);
}