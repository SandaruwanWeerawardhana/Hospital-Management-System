package edu.icet.hospital_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "prescription")
public class PrescriptionEntity {
    @Id
    private Integer prescription_id;
    private Integer patient_id;
    private Integer doctor_id;
    private String medicine;
    private String dosage;
    private String duration;
}
