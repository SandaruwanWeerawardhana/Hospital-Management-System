package edu.icet.hospital_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Doctor {
    private Integer doctor_id;
    private String name;
    private String specialty;
    private String availability;
    private String qualifications;
    private String contact_details;
    private String email;
    private String password;
}
