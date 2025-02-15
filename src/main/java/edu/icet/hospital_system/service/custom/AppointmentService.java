package edu.icet.hospital_system.service.custom;

import edu.icet.hospital_system.dto.Appointment;
import edu.icet.hospital_system.service.SuperService;

import java.util.List;

public interface AppointmentService extends SuperService {
    boolean add(Appointment appointment);

    boolean delete(String id);

    boolean update(String id, Appointment appointment);

    List<Appointment> getAll();

    List<Appointment> getID(Integer id);

    List<Appointment> getPatientByID(Integer id);


}
