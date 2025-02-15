package edu.icet.hospital_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Count {
    private Integer doctorCount;
    private Integer patientCount;
    private Integer Income;
}
