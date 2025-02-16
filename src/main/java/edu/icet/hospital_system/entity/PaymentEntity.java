package edu.icet.hospital_system.entity;

import jakarta.persistence.*;
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
    @Column(name = "bill_id")
    private Integer bill_id;

    @Column(name = "patient_id")
    private Integer patient_id;

    @Column(name = "total_amount")
    private Double total_amount;

    @Column(name = "payment_status")
    private String payment_status;

    @Column(name = "generated_date")
    private Date generated_date;

}
