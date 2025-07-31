package com.webtab.shecpsims.service.bigdata;

import com.webtab.shecpsims.model.entity.bigdata.PatientHealthMetrics;

import java.util.List;

public interface PatientHealthService {
    List<PatientHealthMetrics> getLast7DaysMetrics(Integer patientId);
    Object callFlaskPredict(Integer patientId, List<PatientHealthMetrics> metrics);
}