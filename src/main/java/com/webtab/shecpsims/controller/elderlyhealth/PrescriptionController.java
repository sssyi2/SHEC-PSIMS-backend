package com.webtab.shecpsims.controller.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.MedicalMessage;
import com.webtab.shecpsims.model.entity.elderlyhealth.Prescription;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.MedicalMessageService;
import com.webtab.shecpsims.service.elderlyhealth.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private MedicalMessageService messageService;

    @GetMapping("/{patientId}")
    public R getPrescriptionsByPatientId(@PathVariable Integer patientId) {
        List<Prescription> prescriptions = prescriptionService.findByPatientId(patientId);
        return prescriptions.isEmpty() ? R.error(404).msg("该病人没有医嘱信息") : R.ok().data(prescriptions);
    }

    // 添加新的医嘱详情查询接口
    @GetMapping("/detail/{prescriptionId}")
    public R getPrescriptionById(@PathVariable Integer prescriptionId,
                                 @RequestParam Integer patientId) {
        Prescription prescription = prescriptionService.findByIdAndPatientId(prescriptionId, patientId);
        if (prescription == null) {
            return R.error(404).msg("医嘱不存在");
        }
        return R.ok().data(prescription);
    }

    @PostMapping
    public R createPrescription(@RequestBody Prescription prescription) {
        prescriptionService.save(prescription);
        return R.ok().msg("创建成功");
    }

    @PutMapping("/{patientId}/{prescriptionId}")
    public R updatePrescription(@PathVariable Integer patientId,
                                @PathVariable Integer prescriptionId,
                                @RequestBody Prescription prescription) {
        prescription.setPrescriptionId(prescriptionId);
        prescription.setPatientId(patientId);
        prescriptionService.update(prescription);
        return R.ok().msg("更新成功");
    }

    //删除特定医嘱
    @DeleteMapping("/{patientId}/{prescriptionId}")
    public R deletePrescription(@PathVariable Integer patientId, @PathVariable Integer prescriptionId) {
        prescriptionService.delete(prescriptionId, patientId);
        return R.ok().msg("删除成功");
    }

    //删除该病人的所有医嘱
    @DeleteMapping("/{patientId}")
    public R deletePrescription(@PathVariable Integer patientId) {
        prescriptionService.delete(patientId);
        return R.ok().msg("删除成功");
    }

    //发送医嘱
    @PostMapping("/send/{prescriptionId}")
    public R sendPrescription(@PathVariable Integer prescriptionId,
                              @RequestParam Integer patientId,
                              @RequestParam Integer doctorId) {
        try {// 创建消息记录（不设置send_time，数据库会自动填充）
            MedicalMessage message = new MedicalMessage();
            message.setPatientId(patientId);
            message.setDoctorId(doctorId);
            message.setPrescriptionId(prescriptionId);
            message.setStatus("未读");

            // 保存消息
            messageService.save(message);

            return R.ok().msg("医嘱发送成功");
        } catch (Exception e) {
            return R.error(500).msg("发送失败: " + e.getMessage());
        }
    }

    @GetMapping("/{patientId}/{prescriptionId}/with-status")
    public R getPrescriptionWithStatus(@PathVariable Integer patientId,
                                       @PathVariable Integer prescriptionId) {
        Prescription prescription = prescriptionService.findByIdWithMessageStatus(prescriptionId, patientId);
        if (prescription == null) {
            return R.error(404).msg("医嘱不存在");
        }
        return R.ok().data(prescription);
    }
}
