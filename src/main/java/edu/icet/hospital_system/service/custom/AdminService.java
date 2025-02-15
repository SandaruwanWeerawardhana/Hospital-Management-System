package edu.icet.hospital_system.service.custom;

import edu.icet.hospital_system.dto.Admin;
import edu.icet.hospital_system.service.SuperService;

import java.util.List;

public interface AdminService extends SuperService {
    boolean addAdmin(Admin admin);

    boolean deleteAdmin(Integer id);

    boolean updateAdmin(Admin admin);

    List<Admin> getAll();

    Admin getEmail(String email);
}
