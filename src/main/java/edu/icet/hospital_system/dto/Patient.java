package edu.icet.hospital_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Patient {
    private Integer patient_id;
    private String name;
    private Integer age;
    private String gender;
    private String medical_history;
    private String emergency_contact;
    private String email;
    private String password;
}
