package edu.icet.hospital_system.repository.custom;

import edu.icet.hospital_system.entity.AdminEntity;
import edu.icet.hospital_system.entity.PatientEntity;
import edu.icet.hospital_system.repository.CrudDao;

import java.util.List;

public interface AdminDao extends CrudDao<AdminEntity,String> {
    AdminEntity getEmail(String email);
}
