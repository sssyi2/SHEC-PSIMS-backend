package com.webtab.shecpsims.service.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.PatientManagement;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;

import java.util.List;

public interface PatientManagementService {
    R save(PatientManagement patientManagement);
    R deleteByDoctorIdAndPatientId(Integer doctorId, Integer patientId);
    PatientManagement findByDoctorIdAndPatientId(Integer doctorId, Integer patientId);
    List<PatientManagement> findByDoctorId(Integer doctorId);
}