package com.webtab.shecpsims.service.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.MedicalMessage;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;

public interface MedicalMessageService {
    R save(MedicalMessage message);
    R markAsRead(Integer messageId);
    R findByPatientId(Integer patientId);
    R getMessageById(Integer messageId);
}