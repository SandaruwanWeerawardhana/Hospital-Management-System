package edu.icet.hospital_system.service.custom.impl;

import edu.icet.hospital_system.dto.Payment;
import edu.icet.hospital_system.entity.PaymentEntity;
import edu.icet.hospital_system.repository.DaoFactory;
import edu.icet.hospital_system.repository.custom.PaymentDao;
import edu.icet.hospital_system.service.custom.PaymentService;
import edu.icet.hospital_system.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    PaymentDao paymentDao = DaoFactory.getInstance().getDaoType(DaoType.PAYMENT);

    @Override
    public boolean add(Payment payment) {
        PaymentEntity entity = new ModelMapper().map(payment,PaymentEntity.class);
        return paymentDao.add(entity);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(String id, Payment payment) {
        return false;
    }

    @Override
    public List<Payment> getAll(Integer id) {
        List<PaymentEntity> all = paymentDao.getAll(id);
        List<Payment> payments = new ArrayList<>();
        all.forEach(p -> payments.add((new ModelMapper().map(p, Payment.class))));
        return payments;
    }

    @Override
    public List<Payment> getAllData() {
        List<PaymentEntity> all = paymentDao.getAllData();
        List<Payment> payments = new ArrayList<>();
        all.forEach(p -> payments.add((new ModelMapper().map(p, Payment.class))));
        return payments;
    }

    @Override
    public Double getTotIncome() {
        System.out.println(paymentDao.getTotIncome());
        return paymentDao.getTotIncome();
    }
}
