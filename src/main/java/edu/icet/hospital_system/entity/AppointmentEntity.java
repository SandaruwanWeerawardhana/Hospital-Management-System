package edu.icet.hospital_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "appointment")
public class AppointmentEntity {
    @Id
    private Integer appointment_id;

    @Column(name = "patient_id")
    private Integer patient_id;

    @Column(name = "doctor_id")
    private Integer doctor_id;

    @Column(name = "appointment_date")
    private LocalDate appointment_date;

    @Column(name = "time")
    private String time;

}
