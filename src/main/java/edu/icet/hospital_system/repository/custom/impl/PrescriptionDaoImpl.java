package edu.icet.hospital_system.repository.custom.impl;

import edu.icet.hospital_system.dto.Prescription;
import edu.icet.hospital_system.entity.AppointmentEntity;
import edu.icet.hospital_system.entity.PrescriptionEntity;
import edu.icet.hospital_system.repository.custom.PrescriptionDao;
import edu.icet.hospital_system.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PrescriptionDaoImpl implements PrescriptionDao {
    @Override
    public boolean update(PrescriptionEntity entity) {
        return false;
    }

    @Override
    public boolean add(PrescriptionEntity entity) {
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
        return false;
    }


    @Override
    public List<PrescriptionEntity> getAll(Integer id) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction tx = session.beginTransaction();
            Query<PrescriptionEntity> query = session.createQuery(
                    "FROM prescription where doctor_id =:id", PrescriptionEntity.class);
            query.setParameter("id", id);
            List<PrescriptionEntity> prescriptionEntity = query.list();
            tx.commit();
            return prescriptionEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PrescriptionEntity> getAllData() {
        return List.of();
    }

    @Override
    public List<PrescriptionEntity> getAllByPatientID(Integer id) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction tx = session.beginTransaction();
            Query<PrescriptionEntity> query = session.createQuery(
                    "FROM prescription where patient_id =:id", PrescriptionEntity.class);
            query.setParameter("id", id);
            List<PrescriptionEntity> prescriptionEntity = query.list();
            tx.commit();
            return prescriptionEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
