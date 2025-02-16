package edu.icet.hospital_system.repository.custom.impl;

import edu.icet.hospital_system.entity.PaymentEntity;
import edu.icet.hospital_system.repository.custom.PaymentDao;
import edu.icet.hospital_system.util.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public boolean add(PaymentEntity entity) {
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
    public boolean update(PaymentEntity entity) {
        return false;
    }



    @Override
    public List<PaymentEntity> getAll(Integer id) {
        Session session = HibernateConfig.getSession();
        Transaction tx = session.beginTransaction();
        Query<PaymentEntity> query = session.createQuery("FROM billing where patient_id =: id", PaymentEntity.class);
        query.setParameter("id",id);
        List<PaymentEntity> paymentEntities = query.list();
        tx.commit();
        return paymentEntities;
    }

    @Override
    public List<PaymentEntity> getAllData() {
        Session session = HibernateConfig.getSession();
        Transaction tx = session.beginTransaction();
        Query<PaymentEntity> query = session.createQuery("FROM billing ", PaymentEntity.class);
        List<PaymentEntity> paymentEntities = query.list();
        tx.commit();
        return paymentEntities;
    }

    @Override
    public Double getTotIncome() {
        try (Session session = HibernateConfig.getSession()) {
            return session.createQuery("SELECT SUM(p.total_amount) FROM billing p", Double.class)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

}
