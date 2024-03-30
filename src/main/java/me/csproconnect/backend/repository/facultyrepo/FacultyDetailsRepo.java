package me.csproconnect.backend.repository.facultyrepo;

import me.csproconnect.backend.model.facultymodel.FacultyDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyDetailsRepo extends MongoRepository<FacultyDetails, String> {
    FacultyDetails findByEmployeeId(long employeeId);
}

