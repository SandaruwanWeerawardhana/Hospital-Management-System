package edu.icet.hospital_system.repository;

import edu.icet.hospital_system.repository.custom.impl.*;
import edu.icet.hospital_system.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance == null ? instance = new DaoFactory() : instance;
    }

    public <T extends SuperDao> T getDaoType(DaoType type) {
        switch (type) {
            case ADMIN:
                return (T) new AdminDaoImpl();
            case PATIENT:
                return (T) new PatientDaoImpl();
            case DOCTOR:
                return (T) new DoctorDaoImpl();
            case APPOINTMENT:
                return (T) new AppointmentDaoImpl();
            case PRESCRIPTION:
                return (T) new PrescriptionDaoImpl();
            case PAYMENT:
                return (T) new PaymentDaoImpl();
        }
        return null;
    }
}
