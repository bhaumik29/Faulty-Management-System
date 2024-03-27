package me.csproconnect.backend.repository.adminrepo;

import me.csproconnect.backend.model.adminmodel.AdminDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDetailsRepo extends MongoRepository<AdminDetails, String> {
    AdminDetails findByEmployeeId(String employeeId);
}
