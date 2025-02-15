package edu.icet.hospital_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Payment {
    private Integer bill_id;
    private Integer patient_id;
    private Double total_amount;
    private String payment_status;
    private Date generated_date;

}
