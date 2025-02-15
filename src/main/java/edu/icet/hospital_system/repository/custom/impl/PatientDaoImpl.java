package edu.icet.hospital_system.repository.custom.impl;

import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.entity.AppointmentEntity;
import edu.icet.hospital_system.entity.DoctorEntity;
import edu.icet.hospital_system.entity.PatientEntity;
import edu.icet.hospital_system.repository.custom.PatientDao;
import edu.icet.hospital_system.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PatientDaoImpl implements PatientDao {

    @Override
    public boolean add(PatientEntity entity) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(entity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                PatientEntity patientEntity = session.get(PatientEntity.class, id);
                if (patientEntity != null) {
                    session.remove(patientEntity);
                    transaction.commit();
                    return true;
                }
                return false;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean update(PatientEntity entity) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                PatientEntity user = session.get(PatientEntity.class, entity.getPatient_id());
                if (user == null) return false;
                user.setName(entity.getName());
                user.setAge(entity.getAge());
                user.setGender(entity.getGender());
                user.setEmergency_contact(entity.getEmergency_contact());
                user.setMedical_history(entity.getMedical_history());
                user.setEmail(entity.getEmail());
                user.setPassword(entity.getPassword());
                session.merge(user);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public List<PatientEntity> getAll(Integer id) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction tx = session.beginTransaction();
            Query<PatientEntity> query = session.createQuery(
                    "FROM patient where patient_id =:id", PatientEntity.class);
            query.setParameter("id", id);
            List<PatientEntity> appointmentEntities = query.list();
            tx.commit();
            return appointmentEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<PatientEntity> getAllData() {
        Session session = HibernateConfig.getSession();
        Transaction tx = session.beginTransaction();
        Query<PatientEntity> query = session.createQuery("FROM patient", PatientEntity.class);
        List<PatientEntity> patientEntities = query.list();
        tx.commit();
        return patientEntities;
    }

    @Override
    public List<PatientEntity> getPatientId() {

        return List.of();
    }

    @Override
    public PatientEntity getEmail(String email) {
        try (Session session = HibernateConfig.getSession()) {
            return session.createQuery("FROM patient WHERE email=:email", PatientEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getCount() {
        try(Session session = HibernateConfig.getSession()){
            return session.createQuery("SELECT COUNT(p.patient_id) FROM patient p",Long.class)
                    .uniqueResult()
                    .intValue();
        }catch (Exception e){
            return 0;
        }
    }
}
