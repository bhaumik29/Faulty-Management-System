package me.csproconnect.backend.service.otherservice;

import me.csproconnect.backend.model.othermodel.Subject;
import me.csproconnect.backend.repository.otherrepo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public boolean deleteSubject(String id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
