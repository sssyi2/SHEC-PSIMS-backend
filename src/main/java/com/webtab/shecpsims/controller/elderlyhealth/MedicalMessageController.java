package com.webtab.shecpsims.controller.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.MedicalMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-messages")
public class MedicalMessageController {
    @Autowired
    private MedicalMessageService messageService;

    @GetMapping
    public R getMessagesByPatientId(@RequestParam Integer patientId) {
        return messageService.findByPatientId(patientId);
    }

    //标记已读
    @PutMapping("/{messageId}/read")
    public R markMessageAsRead(@PathVariable Integer messageId) {
        return messageService.markAsRead(messageId);
    }

    @GetMapping("/{messageId}")
    public R getMessageById(@PathVariable Integer messageId) {
        return messageService.getMessageById(messageId);
    }
}