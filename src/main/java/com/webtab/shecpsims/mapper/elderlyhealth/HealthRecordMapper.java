package com.webtab.shecpsims.mapper.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.HealthRecord;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface HealthRecordMapper {
    @Insert("INSERT INTO health_record (patient_id, name, gender, age, birth_date, " +
            "id_number, contact_number, address, family_medical_history, " +
            "allergy_history, past_medical_history, height, weight, " +
            "blood_pressure, heart_rate,create_date,update_date) VALUES " +
            "(#{patientId}, #{name}, #{gender}, #{age}, #{birthDate}, #{idNumber}, " +
            "#{contactNumber}, #{address}, #{familyMedicalHistory}, #{allergyHistory}, " +
            "#{pastMedicalHistory}, #{height}, #{weight}, #{bloodPressure}, #{heartRate}, #{createDate}, #{updateDate})")
    int insert(HealthRecord healthRecord);

    @Update("UPDATE health_record SET name = #{name}, gender = #{gender}, " +
            "age = #{age}, birth_date = #{birthDate}, id_number = #{idNumber}, " +
            "contact_number = #{contactNumber}, address = #{address}, " +
            "family_medical_history = #{familyMedicalHistory}, allergy_history = #{allergyHistory}, " +
            "past_medical_history = #{pastMedicalHistory}, height = #{height}, weight = #{weight}, " +
            "blood_pressure = #{bloodPressure}, heart_rate = #{heartRate},create_date = #{createDate},update_date = #{updateDate}" +
            "WHERE patient_id = #{patientId} AND health_record_id = #{healthRecordId}")
    int updateByIdAndPatientId(HealthRecord healthRecord);

    @Delete("DELETE FROM health_record WHERE patient_id = #{patientId} AND health_record_id = #{healthRecordId}")
    int deleteByIdAndPatientId(@Param("healthRecordId") Integer healthRecordId, @Param("patientId") Integer patientId);

    @Select("SELECT * FROM health_record WHERE patient_id = #{patientId}")
    List<HealthRecord> selectByPatientId(Integer patientId);

    @Select("SELECT * FROM health_record LIMIT #{page}, #{limit}")
    List<HealthRecord> selectAll(@Param("page") int offset, @Param("limit") int limit);
}