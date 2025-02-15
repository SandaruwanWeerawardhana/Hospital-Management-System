package edu.icet.hospital_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "patient")

public class PatientEntity {
    @Id
    private Integer patient_id;
    private String name;
    private Integer age;
    private String gender;
    private String medical_history;
    private String emergency_contact;
    private String email;
    private String password;
}
