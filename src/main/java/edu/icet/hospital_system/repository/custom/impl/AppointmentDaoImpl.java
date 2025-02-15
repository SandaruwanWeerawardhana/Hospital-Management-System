package edu.icet.hospital_system.repository.custom.impl;

import edu.icet.hospital_system.entity.AppointmentEntity;
import edu.icet.hospital_system.repository.custom.AppointmentDao;
import edu.icet.hospital_system.util.HibernateConfig;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {
    @Override
    public boolean add(AppointmentEntity entity) {
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
    public boolean update(AppointmentEntity entity) {
        return false;
    }



    @Override
    public List<AppointmentEntity> getAll(Integer id) {
        return List.of();
    }

    @Override
    public List<AppointmentEntity> getAllData() {
        Session session = HibernateConfig.getSession();
        Transaction tx = session.beginTransaction();
        Query<AppointmentEntity> query = session.createQuery("FROM appointment", AppointmentEntity.class);
        List<AppointmentEntity> appointmentEntities = query.list();
        tx.commit();
        return appointmentEntities;
    }

    @Override
    public List<AppointmentEntity> getByID(Integer id) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction tx = session.beginTransaction();
            Query<AppointmentEntity> query = session.createQuery(
                    "FROM appointment where doctor_id =:id", AppointmentEntity.class);
            query.setParameter("id", id);
            List<AppointmentEntity> appointmentEntities = query.list();
            tx.commit();

            return appointmentEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AppointmentEntity> getPatientByID(Integer id) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction tx = session.beginTransaction();
            Query<AppointmentEntity> query = session.createQuery(
                    "FROM appointment where patient_id =:id", AppointmentEntity.class);
            query.setParameter("id", id);
            List<AppointmentEntity> appointmentEntities = query.list();
            tx.commit();

            return appointmentEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
