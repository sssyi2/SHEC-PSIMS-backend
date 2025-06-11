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

    @Select("SELECT * FROM prescription WHERE patient_id = #{patientId}")
    List<Prescription> selectByPatientId(Integer patientId);

    @Select("SELECT * FROM prescription LIMIT #{page}, #{limit}")
    List<Prescription> selectAll(@Param("page") int offset, @Param("limit") int limit);
}