package edu.icet.hospital_system.service.custom.impl;

import edu.icet.hospital_system.dto.Appointment;
import edu.icet.hospital_system.dto.Prescription;
import edu.icet.hospital_system.entity.AppointmentEntity;
import edu.icet.hospital_system.entity.PrescriptionEntity;
import edu.icet.hospital_system.repository.DaoFactory;
import edu.icet.hospital_system.repository.custom.PrescriptionDao;
import edu.icet.hospital_system.service.custom.PrescriptionService;
import edu.icet.hospital_system.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrescriptionServiceImpl implements PrescriptionService {
    PrescriptionDao prescriptionDao = DaoFactory.getInstance().getDaoType(DaoType.PRESCRIPTION);
    @Override
    public boolean add(Prescription prescription) {
        PrescriptionEntity entity = new ModelMapper().map(prescription,PrescriptionEntity.class);
        return prescriptionDao.add(entity);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(String id, Prescription prescription) {
        return false;
    }

    @Override
    public List<Prescription> getAll(Integer id) {
        List<PrescriptionEntity> prescriptionEntities = prescriptionDao.getAll(id);
        if(prescriptionEntities==null)return null;
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptionEntities.forEach(entity -> prescriptions.add(new ModelMapper().map(entity,Prescription.class)));
        return prescriptions;

    }

    @Override
    public List<Prescription> getAllByPatientID(Integer id) {
        List<PrescriptionEntity> prescriptionEntities = prescriptionDao.getAllByPatientID(id);
        if(prescriptionEntities==null)return null;
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptionEntities.forEach(entity -> prescriptions.add(new ModelMapper().map(entity,Prescription.class)));
        return prescriptions;
    }
}
