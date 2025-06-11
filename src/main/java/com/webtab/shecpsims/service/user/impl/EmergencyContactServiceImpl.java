package com.webtab.shecpsims.service.user.impl;

import com.webtab.shecpsims.model.entity.elderlyhealth.EmergencyContact;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.mapper.elderlyhealth.EmergencyContactMapper;
import com.webtab.shecpsims.service.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService {

    @Autowired
    private EmergencyContactMapper emergencyContactMapper;

    @Override
    public R saveOrUpdate(EmergencyContact emergencyContact) {
        EmergencyContact existingContact = emergencyContactMapper.selectByPatientId(emergencyContact.getPatientId());
        if (existingContact == null) {
            int insertResult = emergencyContactMapper.insert(emergencyContact);
            return insertResult > 0 ? R.ok().msg("创建成功") : R.error(500).msg("创建失败");
        } else {
            int updateResult = emergencyContactMapper.updateByPatientId(emergencyContact);
            return updateResult > 0 ? R.ok().msg("更新成功") : R.error(500).msg("更新失败");
        }
    }

    @Override
    public R delete(Integer patientId) {
        int deleteResult = emergencyContactMapper.deleteByPatientId(patientId);
        return deleteResult > 0 ? R.ok().msg("删除成功") : R.error(500).msg("删除失败");
    }

    @Override
    public EmergencyContact findByPatientId(Integer patientId) {
        return emergencyContactMapper.selectByPatientId(patientId);
    }

    @Override
    public List<EmergencyContact> findAll(int page, int limit) {
        return emergencyContactMapper.selectAll(page, limit);
    }
}