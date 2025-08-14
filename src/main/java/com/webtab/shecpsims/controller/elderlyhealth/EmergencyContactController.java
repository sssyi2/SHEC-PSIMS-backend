package com.webtab.shecpsims.controller.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.EmergencyContact;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency-contacts")
public class EmergencyContactController {

    @Autowired
    private EmergencyContactService emergencyContactService;

    @GetMapping("/{patientId}")
    public R getEmergencyContact(@PathVariable Integer patientId) {
        List<EmergencyContact> emergencyContact = emergencyContactService.findByPatientId(patientId);
        return emergencyContact != null ? R.ok().data(emergencyContact) : R.error(404).msg( "紧急联系人不存在");
    }

    @PostMapping
    public R createNewEmergencyContact(@RequestBody EmergencyContact emergencyContact) {
        emergencyContactService.save(emergencyContact);
        return R.ok().msg("操作成功");
    }

    @PutMapping("/{contactId}")
    public R updateEmergencyContact(@PathVariable Integer contactId,
                                    @RequestBody EmergencyContact emergencyContact) {
        emergencyContact.setContactId(contactId);
        emergencyContactService.update(emergencyContact);
        return R.ok().msg("更新成功");
    }

    @DeleteMapping("/{contactId}")
    public R deleteEmergencyContact(@PathVariable Integer contactId) {
        emergencyContactService.delete(contactId);
        return R.ok().msg("删除成功");
    }
}