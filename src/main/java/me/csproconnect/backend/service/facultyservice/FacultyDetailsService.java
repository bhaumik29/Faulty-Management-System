package me.csproconnect.backend.service.facultyservice;

import me.csproconnect.backend.model.facultymodel.FacultyDetails;
import me.csproconnect.backend.repository.facultyrepo.FacultyDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyDetailsService {

    @Autowired
    private FacultyDetailsRepo facultyRepository;

    public List<FacultyDetails> getDetails() {
        return facultyRepository.findAll();
    }

    public FacultyDetails addDetails(FacultyDetails faculty) {
        if (facultyRepository.findByEmployeeId(faculty.getEmployeeId()) != null) {
            throw new RuntimeException("Faculty with this employee ID already exists");
        }
        return facultyRepository.save(faculty);
    }

    public FacultyDetails updateDetails(String id, FacultyDetails faculty) {
        if (!facultyRepository.existsById(id)) {
            throw new RuntimeException("No faculty found with this ID");
        }
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteDetails(String id) {
        facultyRepository.deleteById(id);
    }

    public long count() {
        return facultyRepository.count();
    }
}
