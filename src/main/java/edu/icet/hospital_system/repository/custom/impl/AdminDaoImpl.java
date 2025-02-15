package edu.icet.hospital_system.repository.custom.impl;

import edu.icet.hospital_system.entity.AdminEntity;
import edu.icet.hospital_system.entity.DoctorEntity;
import edu.icet.hospital_system.repository.custom.AdminDao;
import edu.icet.hospital_system.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean add(AdminEntity entity) {
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
                AdminEntity Entity = session.get(AdminEntity.class, id);
                if (Entity != null) {
                    session.remove(Entity);
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
    public boolean update(AdminEntity entity) {
        try (Session session = HibernateConfig.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                AdminEntity user = session.get(AdminEntity.class, entity.getAdmin_id());
                if (user == null) return false;
                user.setName(entity.getName());
                user.setNic(entity.getNic());
                user.setAddress(entity.getAddress());
                user.setContact_details(entity.getContact_details());
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
    public List<AdminEntity> getAll(Integer id) {
        return List.of();
    }

    @Override
    public List<AdminEntity> getAllData() {
        return List.of();
    }

    @Override
    public AdminEntity getEmail(String email) {
        try (Session session = HibernateConfig.getSession()) {
            return session.createQuery("FROM admin WHERE email=:email", AdminEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
