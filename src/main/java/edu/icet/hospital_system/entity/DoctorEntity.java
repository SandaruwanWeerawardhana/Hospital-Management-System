package edu.icet.hospital_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "doctor")
public class DoctorEntity {
    @Id
    private Integer doctor_id;
    @Column(name = "name")
    private String name;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "availability")
    private String availability;

    @Column(name = "qualifications")
    private String qualifications;

    @Column(name = "contact_details")
    private String contact_details;

    @Column(name = "price")
    private Double price;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
