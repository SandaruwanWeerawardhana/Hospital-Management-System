package edu.icet.hospital_system.service.custom;

import edu.icet.hospital_system.dto.Payment;
import edu.icet.hospital_system.dto.Prescription;
import edu.icet.hospital_system.service.SuperService;

import java.util.List;

public interface PrescriptionService extends SuperService {
    boolean add(Prescription prescription);

    boolean delete(String id);

    boolean update(String id, Prescription prescription);

    List<Prescription> getAll(Integer id);

    List<Prescription> getAllByPatientID(Integer id);
}
