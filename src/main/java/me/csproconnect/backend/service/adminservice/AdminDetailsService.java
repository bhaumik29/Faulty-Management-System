package me.csproconnect.backend.service.adminservice;

import me.csproconnect.backend.model.adminmodel.AdminDetails;
import me.csproconnect.backend.repository.adminrepo.AdminDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService {

    @Autowired
    private AdminDetailsRepo adminRepository;

    public AdminDetails findByEmployeeId(int employeeId) {
        return adminRepository.findByEmployeeId(employeeId);
    }

    public AdminDetails addAdmin(AdminDetails admin) {
        if (adminRepository.findByEmployeeId(admin.getEmployeeId()) != null) {
            throw new RuntimeException("Admin with this employee ID already exists");
        }
        return adminRepository.save(admin);
    }

    public AdminDetails updateAdmin(String id, AdminDetails admin) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("No admin found with this ID");
        }
        admin.setId(id);
        return adminRepository.save(admin);
    }

    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }
}
