package me.csproconnect.backend.repository.otherrepo;

import me.csproconnect.backend.model.othermodel.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepo extends MongoRepository<Notice, String> {
}
