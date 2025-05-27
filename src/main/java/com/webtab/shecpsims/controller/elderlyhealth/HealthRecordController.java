package com.webtab.shecpsims.controller.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.HealthRecord;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-records")
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    @GetMapping("/{patientId}")
    public R getHealthRecordsByPatientId(@PathVariable Integer patientId) {
        List<HealthRecord> healthRecords = healthRecordService.findByPatientId(patientId);
        return healthRecords.isEmpty() ? R.error(404).msg("该病人没有健康档案") : R.ok().data(healthRecords);
    }

    @PostMapping
    public R createHealthRecord(@RequestBody HealthRecord healthRecord) {
        healthRecordService.save(healthRecord);
        return R.ok().msg("创建成功");
    }

    @PutMapping("/{patientId}/{healthRecordId}")
    public R updateHealthRecord(@PathVariable Integer patientId,
                                @PathVariable Integer healthRecordId,
                                @RequestBody HealthRecord healthRecord) {
        healthRecord.setHealthRecordId(healthRecordId);
        healthRecord.setPatientId(patientId);
        healthRecordService.update(healthRecord);
        return R.ok().msg("更新成功");
    }

    @DeleteMapping("/{patientId}/{healthRecordId}")
    public R deleteHealthRecord( @PathVariable Integer patientId,@PathVariable Integer healthRecordId) {
        healthRecordService.delete(healthRecordId,patientId);
        return R.ok().msg("删除成功");
    }
}
