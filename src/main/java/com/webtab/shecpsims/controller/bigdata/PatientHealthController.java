package com.webtab.shecpsims.controller.bigdata;

import com.webtab.shecpsims.common.BaseResponse;
import com.webtab.shecpsims.common.ResultUtils;
import com.webtab.shecpsims.model.entity.bigdata.PatientHealthMetrics;
import com.webtab.shecpsims.service.bigdata.PatientHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/patient")
public class PatientHealthController {

    @Autowired
    private PatientHealthService patientHealthService;

    @PostMapping("/predictHealth")
    public BaseResponse<?> predictHealth(@RequestBody Map<String, Object> req) {
        Integer patientId = (Integer) req.get("patient_id");
        if (patientId == null) {
            return ResultUtils.error(400, "patient_id不能为空");
        }
        List<PatientHealthMetrics> metrics = patientHealthService.getLast7DaysMetrics(patientId);
        Object flaskResp = patientHealthService.callFlaskPredict(patientId, metrics);

        Map<String, Object> result = new HashMap<>();
        result.put("metrics", metrics);    // 最近七天的健康数据
        result.put("predict", flaskResp);  // 预测结果
    
        return ResultUtils.success(result);
    }
}