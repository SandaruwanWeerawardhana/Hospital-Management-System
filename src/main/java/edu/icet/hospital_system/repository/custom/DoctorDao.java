package edu.icet.hospital_system.repository.custom;

import edu.icet.hospital_system.entity.DoctorEntity;
import edu.icet.hospital_system.repository.CrudDao;

import java.util.List;

public interface DoctorDao extends CrudDao<DoctorEntity, String> {
    boolean delete(Integer s);

    DoctorEntity getEmail(String email);

    List<DoctorEntity> getAll();

    List<DoctorEntity> getAllById(Integer id);

    Integer getDoctorIDCount();

}
