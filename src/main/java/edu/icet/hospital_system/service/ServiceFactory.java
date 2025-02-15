package edu.icet.hospital_system.service;

import edu.icet.hospital_system.service.custom.impl.*;
import edu.icet.hospital_system.util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance == null ? instance = new ServiceFactory() : instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType type) {
        switch (type) {
            case PATIENT:
                return (T) new PatientServiceImpl();
            case DOCTOR:
                return (T) new DoctorServiceImpl();
            case ADMIN:
                return (T) new AdminServiceImpl();
            case APPOINTMENT:
                return (T) new AppointmentServiceImpl();
            case PRESCRIPTION:
                return (T) new PrescriptionServiceImpl();
            case PAYMENT:
                return (T) new PaymentServiceImpl();
        }
        return null;
    }
}
