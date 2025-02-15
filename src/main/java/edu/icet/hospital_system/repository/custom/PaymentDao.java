package edu.icet.hospital_system.repository.custom;

import edu.icet.hospital_system.entity.PaymentEntity;
import edu.icet.hospital_system.repository.CrudDao;

public interface PaymentDao extends CrudDao<PaymentEntity,String> {
    Long getTotIncome();
}
