package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.service.bigdata.ElderlyprofileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class ElderlyprofileController {

    @Autowired
    private ElderlyprofileService elderlyprofileService;

    /**
     * 获取患者总数
     */
    @GetMapping("/count")
    public Integer getTotalPatientCount() {
        return elderlyprofileService.getTotalPatientCount();
    }
}
