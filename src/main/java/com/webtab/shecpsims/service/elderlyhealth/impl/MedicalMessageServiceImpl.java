package com.webtab.shecpsims.service.elderlyhealth.impl;

import com.webtab.shecpsims.mapper.elderlyhealth.MedicalMessageMapper;
import com.webtab.shecpsims.model.entity.elderlyhealth.MedicalMessage;
import com.webtab.shecpsims.model.entity.elderlyhealth.R;
import com.webtab.shecpsims.service.elderlyhealth.MedicalMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalMessageServiceImpl implements MedicalMessageService {

    @Autowired
    public MedicalMessageMapper messageMapper;

    @Override
    public R save(MedicalMessage message) {
        int insertResult = messageMapper.insert(message);
        return insertResult > 0 ? R.ok().msg("创建成功") : R.error(500).msg("创建失败");
    }

    @Override
    public R markAsRead(Integer messageId) {
        try {
            int result = messageMapper.markAsRead(messageId);
            if (result > 0) {
                return R.ok().msg("消息已标记为已读");
            }
            return R.error(404).msg("未找到该消息");
        } catch (Exception e) {
            return R.error(500).msg("标记消息失败: " + e.getMessage());
        }
    }

    @Override
    public R findByPatientId(Integer patientId) {
        try {
            List<MedicalMessage> messages = messageMapper.findByPatientId(patientId);
            if (messages == null || messages.isEmpty()) {
                return R.ok().msg("该患者没有消息记录").data(messages);
            }
            return R.ok().data(messages);
        } catch (Exception e) {
            return R.error(500).msg("获取消息失败: " + e.getMessage());
        }
    }

    @Override
    public R getMessageById(Integer messageId) {
        try {
            MedicalMessage message = messageMapper.findById(messageId);
            if (message == null) {
                return R.error(404).msg("消息不存在");
            }
            return R.ok().data(message);
        } catch (Exception e) {
            return R.error(500).msg("获取消息失败: " + e.getMessage());
        }
    }
}