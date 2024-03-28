package me.csproconnect.backend.service.otherservice;

import me.csproconnect.backend.model.othermodel.Timetable;
import me.csproconnect.backend.repository.otherrepo.TimetableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepo timetableRepository;

    public Optional<Timetable> getTimetable(int semester) {
        return Optional.ofNullable(timetableRepository.findBySemester(semester));
    }

    public Timetable addOrUpdateTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    public boolean deleteTimetable(int semester) {
        Timetable timetable = timetableRepository.findBySemester(semester);
        if (timetable != null) {
            timetableRepository.delete(timetable);
            return true;
        } else {
            return false;
        }
    }
}

