package edu.icet.hospital_system.util;

import edu.icet.hospital_system.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfig {

    private static SessionFactory session = createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        metadataSources.addAnnotatedClass(PatientEntity.class);
        metadataSources.addAnnotatedClass(DoctorEntity.class);
        metadataSources.addAnnotatedClass(AdminEntity.class);
        metadataSources.addAnnotatedClass(AppointmentEntity.class);
        metadataSources.addAnnotatedClass(PaymentEntity.class);
        metadataSources.addAnnotatedClass(PrescriptionEntity.class);

        Metadata builder = metadataSources
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();
        return builder.getSessionFactoryBuilder().build();
    }

    public static Session getSession() {
        return session.openSession();
    }
}


