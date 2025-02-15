package edu.icet.hospital_system.service.custom.impl;

import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.entity.DoctorEntity;
import edu.icet.hospital_system.repository.DaoFactory;
import edu.icet.hospital_system.repository.custom.DoctorDao;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    DoctorDao doctorDao = DaoFactory.getInstance().getDaoType(DaoType.DOCTOR);

    @Override
    public boolean addDoctor(Doctor doctor) {
        DoctorEntity entity = new ModelMapper().map(doctor, DoctorEntity.class);
        return doctorDao.add(entity);
    }

    @Override
    public boolean deleteDoctor(Integer id) {
        return doctorDao.delete(id);
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return doctorDao.update(new ModelMapper().map(doctor,DoctorEntity.class));
    }

    @Override
    public List<Doctor> getAll() {
        List<DoctorEntity> all = doctorDao.getAll();
        List<Doctor> doctors = new ArrayList<>();
        all.forEach(p -> doctors.add((new ModelMapper().map(p, Doctor.class))));
        return doctors;
    }

    @Override
    public List<Doctor> getAllById(Integer id) {
        List<DoctorEntity> all = doctorDao.getAllById(id);
        List<Doctor> doctors = new ArrayList<>();
        all.forEach(p -> doctors.add((new ModelMapper().map(p, Doctor.class))));
        return doctors;
    }

    @Override
    public Doctor doctorValidatioin(String email) {
        DoctorEntity doctorEntity = doctorDao.getEmail(email);
        return doctorEntity==null? null:new ModelMapper().map(doctorEntity,Doctor.class);
    }

    @Override
    public Integer getDoctorIDCount() {
        return doctorDao.getDoctorIDCount();
    }


}
