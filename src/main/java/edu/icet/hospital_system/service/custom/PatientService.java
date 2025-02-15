package edu.icet.hospital_system.service.custom;

import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.service.SuperService;

import java.util.List;

public interface PatientService extends SuperService {
    boolean addPatient(Patient patient);

    boolean deletePatient(Integer id);

    boolean updatePatient(Patient patient);

    List<Patient> getAll(Integer id);

    List<Patient> getAllData();

    Patient getEmail(String email);

    Integer getCount();
}
