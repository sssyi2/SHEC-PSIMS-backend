package com.webtab.shecpsims.mapper.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.MedicalRecord;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface MedicalRecordMapper {
    @Insert("INSERT INTO medical_record (patient_id, name, gender, age, diagnosis, treatment_plan, create_time, update_time) " +
            "VALUES (#{patientId}, #{name}, #{gender}, #{age}, #{diagnosis}, #{treatmentPlan}, #{createTime}, #{updateTime})")
    int insert(MedicalRecord medicalRecord);

    @Update("UPDATE medical_record SET name = #{name}, gender = #{gender}, " +
            "age = #{age}, diagnosis = #{diagnosis}, treatment_plan = #{treatmentPlan}, " +
            "create_time = #{createTime}, update_time = #{updateTime} " +
            "WHERE record_id = #{recordId} AND patient_id = #{patientId}")
    int updateByIdAndPatientId(MedicalRecord medicalRecord);

    @Delete("DELETE FROM medical_record WHERE record_id = #{recordId} AND patient_id = #{patientId}")
    int deleteByIdAndPatientId(@Param("recordId") Integer recordId, @Param("patientId") Integer patientId);

    @Select("SELECT * FROM medical_record WHERE patient_id = #{patientId}")
    List<MedicalRecord> selectByPatientId(Integer patientId);

    @Select("SELECT * FROM medical_record LIMIT #{page}, #{limit}")
    List<MedicalRecord> selectAll(@Param("page") int offset, @Param("limit") int limit);
}