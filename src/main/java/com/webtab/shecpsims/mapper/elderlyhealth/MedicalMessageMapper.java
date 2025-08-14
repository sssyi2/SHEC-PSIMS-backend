package com.webtab.shecpsims.mapper.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.MedicalMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MedicalMessageMapper {
    @Insert("INSERT INTO medical_messages (patient_id, doctor_id, prescription_id, status) " +
            "VALUES (#{patientId}, #{doctorId}, #{prescriptionId}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(MedicalMessage message);

    @Update("UPDATE medical_messages SET status = '已读' WHERE message_id = #{messageId}")
    int markAsRead(Integer messageId);

    @Select("SELECT * FROM medical_messages WHERE patient_id = #{patientId} ORDER BY send_time DESC")
    List<MedicalMessage> findByPatientId(Integer patientId);

    @Select("SELECT * FROM medical_messages WHERE message_id = #{messageId}")
    MedicalMessage findById(Integer messageId);

    @Select("SELECT * FROM medical_messages WHERE prescription_id = #{prescriptionId}")
    MedicalMessage findByPrescriptionId(Integer prescriptionId);
}
