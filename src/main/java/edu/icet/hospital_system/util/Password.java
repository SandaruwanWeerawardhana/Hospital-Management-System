package edu.icet.hospital_system.util;

import org.jasypt.util.text.BasicTextEncryptor;

public class Password {
    private static Password instance;

    private Password() {
    }

    private String key = "1234";

    public static Password getInstance() {
        return instance == null ? instance = new Password() : instance;
    }

    public String encryptPassword(String password) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        return basicTextEncryptor.encrypt(password);
    }

    public String decryptPassword(String password) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        return basicTextEncryptor.decrypt(password);
    }
}
