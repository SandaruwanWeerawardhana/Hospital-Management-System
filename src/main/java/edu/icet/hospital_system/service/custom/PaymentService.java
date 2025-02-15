package edu.icet.hospital_system.service.custom;

import edu.icet.hospital_system.dto.Payment;
import edu.icet.hospital_system.service.SuperService;

import java.util.List;

public interface PaymentService extends SuperService {
    boolean add(Payment payment);

    boolean delete(String id);

    boolean update(String id, Payment payment);

    List<Payment> getAll(Integer id);

    List<Payment> getAllData();

    Long getTotIncome();
}
