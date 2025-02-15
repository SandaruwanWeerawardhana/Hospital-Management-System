package edu.icet.hospital_system.util;

import edu.icet.hospital_system.dto.Doctor;

public class DoctorUtil {
    private static Doctor doctorIsntance;

    private DoctorUtil(){}

    public static void setCurrentDoctor(Doctor doctor) {
        doctorIsntance = doctor;
    }

    public static Doctor getCurrentDoctor() {
        return doctorIsntance;
    }
}
