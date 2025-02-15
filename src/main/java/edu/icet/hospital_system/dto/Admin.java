package edu.icet.hospital_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Admin {
    private Integer admin_id;
    private String name;
    private String nic;
    private String address;
    private String contact_details;
    private String email;
    private String password;
}
