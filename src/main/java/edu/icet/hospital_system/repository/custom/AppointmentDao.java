package edu.icet.hospital_system.repository.custom;

import edu.icet.hospital_system.entity.AppointmentEntity;
import edu.icet.hospital_system.repository.CrudDao;

import java.util.List;

public interface AppointmentDao extends CrudDao<AppointmentEntity,String> {
    List<AppointmentEntity> getByID(Integer id);
    List<AppointmentEntity> getPatientByID(Integer id);
}
