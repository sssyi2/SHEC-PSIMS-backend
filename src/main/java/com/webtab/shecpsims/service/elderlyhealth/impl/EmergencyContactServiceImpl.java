package com.webtab.shecpsims.service.elderlyhealth.impl;

import com.webtab.shecpsims.mapper.elderlyhealth.EmergencyContactMapper;
import com.webtab.shecpsims.model.entity.elderlyhealth.EmergencyContact;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService {

    @Autowired
    private EmergencyContactMapper emergencyContactMapper;

    @Override
    public R save(EmergencyContact emergencyContact) {
        int insertResult = emergencyContactMapper.insert(emergencyContact);
        return insertResult > 0 ? R.ok().msg("创建成功") : R.error(500).msg("创建失败");
    }

    @Override
    public R update(EmergencyContact emergencyContact) {
        int updateResult = emergencyContactMapper.updateByContactId(emergencyContact);
        return updateResult > 0 ? R.ok().msg("更新成功") : R.error(500).msg("更新失败");
    }

    @Override
    public R delete(Integer contactId) {
        int deleteResult = emergencyContactMapper.deleteByContactId(contactId);
        return deleteResult > 0 ? R.ok().msg("删除成功") : R.error(500).msg("删除失败");
    }

    @Override
    public List<EmergencyContact> findByPatientId(Integer patientId) {
        return emergencyContactMapper.selectByPatientId(patientId);
    }

}