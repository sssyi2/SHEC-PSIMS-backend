package com.webtab.shecpsims.mapper.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.EmergencyContact;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EmergencyContactMapper {
    @Insert("INSERT INTO emergency_contact (patient_id, name, contact_name, contact_phone, relation) " +
            "VALUES (#{patientId}, #{name}, #{contactName}, #{contactPhone}, #{relation})")
    int insert(EmergencyContact emergencyContact);

    @Update("UPDATE emergency_contact SET name = #{name}, contact_name = #{contactName}, " +
            "contact_phone = #{contactPhone}, relation = #{relation} WHERE patient_id = #{patientId}")
    int updateByPatientId(EmergencyContact emergencyContact);

    @Delete("DELETE FROM emergency_contact WHERE patient_id = #{patientId}")
    int deleteByPatientId(Integer patientId);

    @Select("SELECT * FROM emergency_contact WHERE patient_id = #{patientId}")
    EmergencyContact selectByPatientId(Integer patientId);

    @Select("SELECT * FROM emergency_contact LIMIT #{page}, #{limit}")
    List<EmergencyContact> selectAll(@Param("page") int offset, @Param("limit") int limit);
}