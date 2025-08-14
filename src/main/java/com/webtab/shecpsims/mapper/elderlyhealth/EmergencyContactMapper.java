package com.webtab.shecpsims.mapper.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.EmergencyContact;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmergencyContactMapper {
    @Insert("INSERT INTO emergency_contact (patient_id, name, contact_name, contact_phone, relation) " +
            "VALUES (#{patientId}, #{name}, #{contactName}, #{contactPhone}, #{relation})")
    int insert(EmergencyContact emergencyContact);

    @Update("UPDATE emergency_contact SET contact_name = #{contactName}, " +
            "contact_phone = #{contactPhone}, relation = #{relation} WHERE contact_id = #{contactId}")
    int updateByContactId(EmergencyContact emergencyContact);

    @Delete("DELETE FROM emergency_contact WHERE contact_id = #{contactId}")
    int deleteByContactId(Integer contactId);

    @Select("SELECT * FROM emergency_contact WHERE patient_id = #{patientId}")
    List<EmergencyContact> selectByPatientId(Integer patientId);

}