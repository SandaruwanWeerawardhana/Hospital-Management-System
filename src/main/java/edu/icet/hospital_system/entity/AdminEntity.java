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
@Entity(name = "admin")
public class AdminEntity {
    @Id
    private Integer admin_id;
    private String name;
    private String nic;
    private String address;
    private String contact_details;
    private String email;
    private String password;
}
