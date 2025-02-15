package edu.icet.hospital_system.dto;

import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Appointment {
    private Integer appointment_id;
    private Integer patient_id;
    private Integer doctor_id;
    private LocalDate appointment_date;
    private String time;

    public Appointment(ObservableList<Appointment> observableListApp) {
    }
}
