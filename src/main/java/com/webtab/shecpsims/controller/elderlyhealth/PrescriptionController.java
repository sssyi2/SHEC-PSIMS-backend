package com.webtab.shecpsims.controller.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.Prescription;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/{patientId}")
    public R getPrescriptionsByPatientId(@PathVariable Integer patientId) {
        List<Prescription> prescriptions = prescriptionService.findByPatientId(patientId);
        return prescriptions.isEmpty() ? R.error(404).msg("该病人没有医嘱信息") : R.ok().data(prescriptions);
    }

    @PostMapping
    public R createPrescription(@RequestBody Prescription prescription) {
        prescriptionService.save(prescription);
        return R.ok().msg("创建成功");
    }

    @PutMapping("/{prescriptionId}")
    public R updatePrescription(@PathVariable Integer prescriptionId, @RequestBody Prescription prescription) {
        prescriptionService.update(prescription);
        return R.ok().msg("更新成功");
    }

    @DeleteMapping("/{prescriptionId}")
    public R deletePrescription(@PathVariable Integer prescriptionId, @RequestParam Integer patientId) {
        prescriptionService.delete(prescriptionId, patientId);
        return R.ok().msg("删除成功");
    }
}
