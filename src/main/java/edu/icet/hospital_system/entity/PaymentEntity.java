package edu.icet.hospital_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "billing")

public class PaymentEntity {
    @Id
    private Integer bill_id;
    private Integer patient_id;
    private Double total_amount;
    private String payment_status;
    private Date generated_date;

}
