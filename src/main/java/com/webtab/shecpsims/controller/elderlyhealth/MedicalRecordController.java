package com.webtab.shecpsims.controller.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.MedicalRecord;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/{patientId}")
    public R getMedicalRecordsByPatientId(@PathVariable Integer patientId) {
        List<MedicalRecord> medicalRecords = medicalRecordService.findByPatientId(patientId);
        return medicalRecords.isEmpty() ? R.error(404).msg("该病人没有电子病历") : R.ok().data(medicalRecords);
    }

    @PostMapping
    public R createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        medicalRecordService.save(medicalRecord);
        return R.ok().msg("创建成功");
    }

    @PutMapping("/{patientId}/{recordId}")
    public R updateMedicalRecord(@PathVariable Integer patientId,
                                 @PathVariable Integer recordId,
                                 @RequestBody MedicalRecord medicalRecord) {
        medicalRecord.setRecordId(recordId);
        medicalRecord.setPatientId(patientId);
        medicalRecordService.update(medicalRecord);
        return R.ok().msg("更新成功");
    }

    //删除特定的电子病历
    @DeleteMapping("/{patientId}/{recordId}")
    public R deleteMedicalRecord(@PathVariable Integer patientId, @PathVariable Integer recordId) {
        medicalRecordService.delete(recordId, patientId);
        return R.ok().msg("删除成功");
    }

    //删除该病人所有的电子病历
    @DeleteMapping("/{patientId}")
    public R deleteMedicalRecords(@PathVariable Integer patientId) {
        medicalRecordService.delete(patientId);
        return R.ok().msg("删除成功");
    }
}
