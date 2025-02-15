package edu.icet.hospital_system.service.custom;

import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.entity.DoctorEntity;
import edu.icet.hospital_system.service.SuperService;

import java.util.List;

public interface DoctorService extends SuperService {
    boolean addDoctor(Doctor doctor);

    boolean deleteDoctor(Integer id);

    boolean updateDoctor(Doctor doctor);

    List<Doctor> getAll();

    List<Doctor> getAllById(Integer id);

    Doctor doctorValidatioin(String email);

    Integer getDoctorIDCount();

}
