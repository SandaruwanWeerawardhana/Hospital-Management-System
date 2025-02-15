package edu.icet.hospital_system.util;

import edu.icet.hospital_system.dto.Patient;

public class PatientUtil {
    private static Patient Isntance;

    private PatientUtil(){}

    public static void set(Patient patient) {
        Isntance = patient;
    }

    public static Patient get() {
        return Isntance;
    }
}
