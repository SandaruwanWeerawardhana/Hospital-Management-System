package edu.icet.hospital_system.service.custom.impl;

import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.entity.DoctorEntity;
import edu.icet.hospital_system.entity.PatientEntity;
import edu.icet.hospital_system.repository.DaoFactory;
import edu.icet.hospital_system.repository.custom.PatientDao;
import edu.icet.hospital_system.service.custom.PatientService;
import edu.icet.hospital_system.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;


public class PatientServiceImpl implements PatientService {
    PatientDao patientDao = DaoFactory.getInstance().getDaoType(DaoType.PATIENT);

    @Override
    public boolean addPatient(Patient patient) {
        PatientEntity patientEntity = new ModelMapper().map(patient, PatientEntity.class);
        return patientDao.add(patientEntity);
    }

    @Override
    public boolean deletePatient(Integer id) {
        return patientDao.delete(id);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return patientDao.update(new ModelMapper().map(patient,PatientEntity.class));
    }

    @Override
    public List<Patient> getAll(Integer id) {
        List<PatientEntity> patientEntityList = patientDao.getAll(id);
        List<Patient> patients = new ArrayList<>();
        patientEntityList.forEach(p -> patients.add(new ModelMapper().map(p, Patient.class)));
        return patients;
    }

    @Override
    public List<Patient> getAllData() {
        List<PatientEntity> patientEntityList = patientDao.getAllData();
        List<Patient> patients = new ArrayList<>();
        patientEntityList.forEach(p -> patients.add(new ModelMapper().map(p, Patient.class)));
        return patients;
    }

    @Override
    public Patient getEmail(String email) {
        PatientEntity patientEntity = patientDao.getEmail(email);
        return patientEntity==null? null : new ModelMapper().map(patientEntity,Patient.class);
    }

    @Override
    public Integer getCount() {
        return patientDao.getCount();
    }


}
