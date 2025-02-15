package edu.icet.hospital_system.service.custom.impl;

import edu.icet.hospital_system.dto.Appointment;
import edu.icet.hospital_system.entity.AppointmentEntity;
import edu.icet.hospital_system.repository.DaoFactory;
import edu.icet.hospital_system.repository.custom.AppointmentDao;
import edu.icet.hospital_system.service.custom.AppointmentService;
import edu.icet.hospital_system.util.DaoType;
import edu.icet.hospital_system.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    AppointmentDao appointmentDao = DaoFactory.getInstance().getDaoType(DaoType.APPOINTMENT);
    @Override
    public boolean add(Appointment appointment) {
        AppointmentEntity entity = new ModelMapper().map(appointment,AppointmentEntity.class);
        return appointmentDao.add(entity);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(String id, Appointment appointment) {
        return false;
    }

    @Override
    public List<Appointment> getAll() {
        List<AppointmentEntity> appointmentEntities = appointmentDao.getAllData();
        if(appointmentEntities==null)return null;
        List<Appointment> appointment = new ArrayList<>();
        appointmentEntities.forEach(entity -> appointment.add(new ModelMapper().map(entity, Appointment.class)));
        return appointment;
    }

    @Override
    public List<Appointment> getID(Integer id) {
        List<AppointmentEntity> appointmentEntities = appointmentDao.getByID(id);
        if(appointmentEntities==null)return null;
        List<Appointment> appointment = new ArrayList<>();
        appointmentEntities.forEach(entity -> appointment.add(new ModelMapper().map(entity, Appointment.class)));
        return appointment;

    }

    @Override
    public List<Appointment> getPatientByID(Integer id) {
        List<AppointmentEntity> appointmentEntities = appointmentDao.getPatientByID(id);
        if(appointmentEntities==null)return null;
        List<Appointment> appointment = new ArrayList<>();
        appointmentEntities.forEach(entity -> appointment.add(new ModelMapper().map(entity, Appointment.class)));
        return appointment;
    }

}
