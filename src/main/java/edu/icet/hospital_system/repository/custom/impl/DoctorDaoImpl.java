package edu.icet.hospital_system.repository.custom.impl;

import edu.icet.hospital_system.entity.DoctorEntity;
import edu.icet.hospital_system.repository.custom.DoctorDao;
import edu.icet.hospital_system.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public boolean add(DoctorEntity entity) {
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
    public boolean update(DoctorEntity entity) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                DoctorEntity user = session.get(DoctorEntity.class, entity.getDoctor_id());
                if (user == null)return false;
                user.setName(entity.getName());
                user.setSpecialty(entity.getSpecialty());
                user.setAvailability(entity.getAvailability());
                user.setQualifications(entity.getQualifications());
                user.setContact_details(entity.getContact_details());
                user.setEmail(entity.getEmail());
                user.setPassword(entity.getPassword());
                session.merge(user);
                session.getTransaction().commit();
                return true;
            }catch (Exception e){
                transaction.rollback();
                return false;
            }
        }
    }


    @Override
    public List<DoctorEntity> getAll(Integer id) {
        return null;
    }

    @Override
    public List<DoctorEntity> getAllData() {
        return List.of();
    }

    @Override
    public boolean delete(Integer s) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                DoctorEntity doctorEntity = session.get(DoctorEntity.class, s);
                if (doctorEntity != null) {
                    session.remove(doctorEntity);
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
    public DoctorEntity getEmail(String email) {
        try (Session session = HibernateConfig.getSession()) {
            return session.createQuery("FROM doctor WHERE email=:email", DoctorEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DoctorEntity> getAll() {
        Session session = HibernateConfig.getSession();
        Transaction tx = session.beginTransaction();
        Query<DoctorEntity> query = session.createQuery("FROM doctor", DoctorEntity.class);
        List<DoctorEntity> doctorEntities = query.list();
        tx.commit();
        return doctorEntities;
    }

    @Override
    public List<DoctorEntity> getAllById(Integer id) {
        Session session = HibernateConfig.getSession();
        Transaction tx = session.beginTransaction();
        Query<DoctorEntity> query = session.createQuery("FROM doctor where doctor_id =: id", DoctorEntity.class);
                query.setParameter("id",id);
        List<DoctorEntity> doctorEntities = query.list();
        tx.commit();
        return doctorEntities;
    }

    @Override
    public Integer getDoctorIDCount() {
        try(Session session = HibernateConfig.getSession()){
            return session.createQuery("SELECT COUNT(p.doctor_id) FROM doctor p",Long.class)
                    .uniqueResult()
                    .intValue();
        }catch (Exception e){
            return 0;
        }
    }


}
