package me.csproconnect.backend.controller.othercontroller;

import me.csproconnect.backend.model.othermodel.ApiResponse_Timetable;
import me.csproconnect.backend.model.othermodel.Timetable;
import me.csproconnect.backend.service.otherservice.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/timetables")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @PostMapping("/getTimetable")
    public ResponseEntity<?> getTimetable(@RequestBody int semester) {
        Optional<Timetable> timetable = timetableService.getTimetable(semester);
        if (timetable.isPresent()) {
            return ResponseEntity.ok(timetable.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse_Timetable(false, "Timetable Not Found"));
        }
    }

    @PostMapping("/addOrUpdateTimetable")
    public ResponseEntity<?> addOrUpdateTimetable(@RequestBody Timetable timetable) {
        Timetable addedOrUpdatedTimetable = timetableService.addOrUpdateTimetable(timetable);
        String message = (addedOrUpdatedTimetable.getId() != null) ? "Timetable Updated!" : "Timetable Added!";
        return ResponseEntity.ok(new ApiResponse_Timetable(true, message));
    }

    @DeleteMapping("/deleteTimetable/{semester}")
    public ResponseEntity<?> deleteTimetable(@PathVariable int semester) {
        boolean deleted = timetableService.deleteTimetable(semester);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse_Timetable(true, "Timetable Deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse_Timetable(false, "No Timetable Exists for Semester " + semester));
        }
    }
}
