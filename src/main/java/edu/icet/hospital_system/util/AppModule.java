package edu.icet.hospital_system.util;

import com.google.inject.AbstractModule;
import edu.icet.hospital_system.repository.custom.*;
import edu.icet.hospital_system.repository.custom.impl.*;
import edu.icet.hospital_system.service.custom.*;
import edu.icet.hospital_system.service.custom.impl.*;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PatientService.class).to(PatientServiceImpl.class);
        bind(PatientDao.class).to(PatientDaoImpl.class);

        bind(AdminService.class).to(AdminServiceImpl.class);
        bind(AdminDao.class).to(AdminDaoImpl.class);

        bind(DoctorService.class).to(DoctorServiceImpl.class);
        bind(DoctorDao.class).to(DoctorDaoImpl.class);

        bind(AppointmentService.class).to(AppointmentServiceImpl.class);
        bind(AppointmentDao.class).to(AppointmentDaoImpl.class);

        bind(PaymentService.class).to(PaymentServiceImpl.class);
        bind(PaymentDao.class).to(PaymentDaoImpl.class);

        bind(PrescriptionService.class).to(PrescriptionServiceImpl.class);
        bind(PrescriptionDao.class).to(PrescriptionDaoImpl.class);

    }
}
