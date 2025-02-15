package edu.icet.hospital_system.repository.custom;

import edu.icet.hospital_system.entity.PatientEntity;
import edu.icet.hospital_system.repository.CrudDao;

import java.util.List;

public interface PatientDao extends CrudDao<PatientEntity, String> {
    List<PatientEntity> getPatientId();

    PatientEntity getEmail(String email);

    Integer getCount();


}
