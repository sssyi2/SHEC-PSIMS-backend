package com.webtab.shecpsims.service;

import com.webtab.shecpsims.model.entity.elderlyhealth.EmergencyContact;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import java.util.List;

public interface EmergencyContactService {
    R saveOrUpdate(EmergencyContact emergencyContact);
    R delete(Integer patientId);
    EmergencyContact findByPatientId(Integer patientId);
    List<EmergencyContact> findAll(int page, int limit);
}