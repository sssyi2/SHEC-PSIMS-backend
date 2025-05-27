package com.webtab.shecpsims.controller.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.EmergencyContact;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emergency-contacts")
public class EmergencyContactController {

    @Autowired
    private EmergencyContactService emergencyContactService;

    @GetMapping("/{patientId}")
    public R getEmergencyContact(@PathVariable Integer patientId) {
        EmergencyContact emergencyContact = emergencyContactService.findByPatientId(patientId);
        return emergencyContact != null ? R.ok().data(emergencyContact) : R.error(404).msg( "紧急联系人不存在");
    }

    @PutMapping
    public R createOrUpdateEmergencyContact(@RequestBody EmergencyContact emergencyContact) {
        if (emergencyContact == null || emergencyContact.getPatientId() == null) {
            return R.error(400).msg("请求参数无效");
        }
        emergencyContactService.saveOrUpdate(emergencyContact);
        return R.ok().msg("操作成功");
    }

    @DeleteMapping("/{patientId}")
    public R deleteEmergencyContact(@PathVariable Integer patientId) {
        if (patientId == null || patientId <= 0) {
            return R.error(400).msg("无效的患者ID");
        }
        emergencyContactService.delete(patientId);
        return R.ok().msg("删除成功");
    }
}