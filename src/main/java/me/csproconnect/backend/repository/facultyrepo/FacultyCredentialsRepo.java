package me.csproconnect.backend.repository.facultyrepo;

import me.csproconnect.backend.model.facultymodel.FacultyDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyCredentialsRepo extends MongoRepository<FacultyDetails, String> {
    FacultyDetails findByLoginid(int loginid);
}

