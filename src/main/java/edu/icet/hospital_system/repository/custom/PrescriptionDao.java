package edu.icet.hospital_system.repository.custom;

import edu.icet.hospital_system.dto.Prescription;
import edu.icet.hospital_system.entity.PrescriptionEntity;
import edu.icet.hospital_system.repository.CrudDao;

import java.util.List;

public interface PrescriptionDao extends CrudDao<PrescriptionEntity,String> {
    List<PrescriptionEntity> getAllByPatientID(Integer id);
}
