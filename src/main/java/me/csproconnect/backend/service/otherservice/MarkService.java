package me.csproconnect.backend.service.otherservice;

import me.csproconnect.backend.model.othermodel.Mark;
import me.csproconnect.backend.repository.otherrepo.MarkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkService {

    @Autowired
    private MarkRepo marksRepository;

    public Mark findByEnrollmentNo(long enrollmentNo) {
        return marksRepository.findByEnrollmentNo(enrollmentNo);
    }

    public Mark addOrUpdateMarks(Mark marks) {
        Mark existingMarks = marksRepository.findByEnrollmentNo(marks.getEnrollmentNo());
        if (existingMarks != null) {
            marks.setId(existingMarks.getId());
        }
        return marksRepository.save(marks);
    }

    public void deleteMarks(String id) {
        marksRepository.deleteById(id);
    }
}
