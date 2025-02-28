package edu.icet.hospital_system.service.custom.impl;

import edu.icet.hospital_system.dto.Admin;
import edu.icet.hospital_system.entity.AdminEntity;
import edu.icet.hospital_system.repository.DaoFactory;
import edu.icet.hospital_system.repository.custom.AdminDao;
import edu.icet.hospital_system.service.custom.AdminService;
import edu.icet.hospital_system.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);

    @Override
    public boolean addAdmin(Admin admin) {
        AdminEntity entity = new ModelMapper().map(admin, AdminEntity.class);
        return adminDao.add(entity);
    }

    @Override
    public boolean deleteAdmin(Integer id) {
        return adminDao.delete(id);
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return adminDao.update(new ModelMapper().map(admin, AdminEntity.class));
    }

    @Override
    public List<Admin> getAll() {
        return List.of();
    }

    @Override
    public Admin getEmail(String email) {
        AdminEntity adminEntity = adminDao.getEmail(email);
        return adminEntity == null ? null : new ModelMapper().map(adminEntity, Admin.class);
    }
}
