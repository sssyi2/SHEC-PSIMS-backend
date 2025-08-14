package com.webtab.shecpsims.mapper.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.Prescription;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PrescriptionMapper {
    @Insert("INSERT INTO prescription (patient_id, record_id, name, medication_info, note, start_date, end_date, create_time, update_time) " +
            "VALUES (#{patientId}, #{recordId}, #{name}, #{medicationInfo}, #{note}, #{startDate}, #{endDate}, #{createTime}, #{updateTime})")
    int insert(Prescription prescription);

    @Update("UPDATE prescription SET record_id = #{recordId}, name = #{name}, " +
            "medication_info = #{medicationInfo}, note = #{note}, start_date = #{startDate}, " +
            "end_date = #{endDate}, create_time = #{createTime}, update_time = #{updateTime} " +
            "WHERE prescription_id = #{prescriptionId} AND patient_id = #{patientId}")
    int updateByIdAndPatientId(Prescription prescription);

    @Delete("DELETE FROM prescription WHERE prescription_id = #{prescriptionId} AND patient_id = #{patientId}")
    int deleteByIdAndPatientId(@Param("prescriptionId") Integer prescriptionId, @Param("patientId") Integer patientId);

    @Delete("DELETE FROM prescription WHERE patient_id = #{patientId}")
    int deletePatientAllPrescription(@Param("patientId") Integer patientId);

    @Select("SELECT * FROM prescription WHERE patient_id = #{patientId}")
    List<Prescription> selectByPatientId(Integer patientId);

    @Select("SELECT p.*, " +
            "       CASE WHEN m.message_id IS NOT NULL THEN 1 ELSE 0 END AS sent, " +
            "       m.send_time " +
            "FROM prescription p " +
            "LEFT JOIN medical_messages m ON p.prescription_id = m.prescription_id " +
            "WHERE p.prescription_id = #{prescriptionId} AND p.patient_id = #{patientId}")
    Prescription findByIdWithMessageStatus(@Param("prescriptionId") Integer prescriptionId,
                                             @Param("patientId") Integer patientId);

    @Select("SELECT * FROM prescription WHERE prescription_id = #{prescriptionId} AND patient_id = #{patientId}")
    Prescription findByIdAndPatientId(@Param("prescriptionId") Integer prescriptionId,
                                      @Param("patientId") Integer patientId);
}