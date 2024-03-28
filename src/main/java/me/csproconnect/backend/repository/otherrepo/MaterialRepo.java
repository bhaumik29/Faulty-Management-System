package me.csproconnect.backend.repository.otherrepo;

import me.csproconnect.backend.model.othermodel.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepo extends MongoRepository<Material, String> {
}
