package edu.icet.hospital_system.util;

import java.util.HashMap;
import java.util.Map;

public class OTPManager {
    private static final Map<String, String> otpStorage = new HashMap<>();

    public static void storeOTP(String email, String otp) {
        otpStorage.put(email, otp);
    }

    public static boolean verifyOTP(String email, String enteredOtp) {
        if (otpStorage.containsKey(email) && otpStorage.get(email).equals(enteredOtp)) {
            otpStorage.remove(email);
            return true;
        }
        return false;
    }

}
