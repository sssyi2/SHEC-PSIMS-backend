package com.webtab.shecpsims.controller.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.PatientManagement;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.PatientManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient-management")
public class PatientManagementController {

    @Autowired
    private PatientManagementService patientManagementService;

    @PostMapping
    public R createPatientManagement(@RequestBody PatientManagement patientManagement) {
        if (patientManagement == null || patientManagement.getDoctorId() == null || patientManagement.getPatientId() == null) {
            return R.error(400).msg("请求参数无效");
        }
        patientManagementService.save(patientManagement);
        return R.ok().msg("创建成功");
    }

    @GetMapping("/patient")//搜索某位病人
    public R getPatientManagement(@RequestParam Integer doctorId, @RequestParam Integer patientId) {
        PatientManagement patientManagement = patientManagementService.findByDoctorIdAndPatientId(doctorId, patientId);
        return patientManagement != null ? R.ok().data(patientManagement) : R.error(404).msg("患者管理信息不存在");
    }

    @GetMapping("/patients")//加载该医生管理的所有病人
    public R getPatientManagement(@RequestParam Integer doctorId) {
        List<PatientManagement> patientManagement = patientManagementService.findByDoctorId(doctorId);
        return patientManagement != null ? R.ok().data(patientManagement) : R.error(404).msg("没有主管病人信息");
    }

    @DeleteMapping
    public R deletePatientManagement(@RequestParam Integer doctorId, @RequestParam Integer patientId) {
        if (doctorId == null || patientId == null) {
            return R.error(400).msg("无效的医生ID或患者ID");
        }
        patientManagementService.deleteByDoctorIdAndPatientId(doctorId, patientId);
        return R.ok().msg("删除成功");
    }
}