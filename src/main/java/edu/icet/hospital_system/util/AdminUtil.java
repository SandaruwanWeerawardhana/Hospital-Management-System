package edu.icet.hospital_system.util;

import edu.icet.hospital_system.dto.Admin;

public class AdminUtil {
    private static Admin isntance;

    private AdminUtil(){}

    public static void setCurrent(Admin admin) {
        isntance = admin;
    }

    public static Admin getCurrent() {
        return isntance;
    }
}
