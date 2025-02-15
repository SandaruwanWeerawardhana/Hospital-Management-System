package edu.icet.hospital_system.util;

import edu.icet.hospital_system.dto.Appointment;

public class AppointmentUtil {
    private static Appointment appointmentIsntance;

    private AppointmentUtil(){}

    public static void setCurrent(Appointment appointment) {
        appointmentIsntance = appointment;
    }

    public static Appointment getCurrent() {
        return appointmentIsntance;
    }
}
