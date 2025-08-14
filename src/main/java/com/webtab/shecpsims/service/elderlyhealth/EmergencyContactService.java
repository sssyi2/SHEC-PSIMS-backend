package com.webtab.shecpsims.service.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.EmergencyContact;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;

import java.util.List;

public interface EmergencyContactService {
    R save(EmergencyContact emergencyContact);
    R update(EmergencyContact emergencyContact);
    R delete(Integer contactId);
    List<EmergencyContact> findByPatientId(Integer patientId);
}