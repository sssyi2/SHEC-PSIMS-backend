package com.webtab.shecpsims.mapper.elderlyhealth;

import com.webtab.shecpsims.model.entity.elderlyhealth.PatientManagement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientManagementMapper {
    @Insert("INSERT INTO patient_management (doctor_id, patient_id, name) " +
            "VALUES (#{doctorId}, #{patientId}, #{name})")
    int insert(PatientManagement patientManagement);

    @Delete("DELETE FROM patient_management WHERE doctor_id = #{doctorId} AND patient_id = #{patientId}")
    int deleteByDoctorIdAndPatientId(Integer doctorId, Integer patientId);

    @Select("SELECT * FROM patient_management WHERE doctor_id = #{doctorId} AND patient_id = #{patientId}")
    PatientManagement selectByDoctorIdAndPatientId(Integer doctorId, Integer patientId);

    @Select("SELECT * FROM patient_management WHERE doctor_id = #{doctorId}")
    List<PatientManagement> selectByDoctorId(Integer doctorId);

}