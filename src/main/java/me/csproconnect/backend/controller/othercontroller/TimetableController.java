package me.csproconnect.backend.controller.othercontroller;

import me.csproconnect.backend.model.othermodel.ApiResponse_Timetable;
import me.csproconnect.backend.model.othermodel.Timetable;
import me.csproconnect.backend.repository.otherrepo.TimetableRepo;
import me.csproconnect.backend.service.otherservice.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/timetables")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @PostMapping("/getTimetable")
    public ResponseEntity<?> getTimetable(@RequestBody Map<String, Integer> request) {
        int semester = request.get("semester");
        Timetable timetable = timetableService.findBySemester(semester);
        if (timetable != null) {
            return ResponseEntity.ok(timetable);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse_Timetable(false, "Timetable Not Found"));
        }
    }

    @PostMapping("/addTimetable")
    public ResponseEntity<?> addTimetable(@RequestBody Timetable timetable) {
        try {
            Timetable existingTimetable = timetableService.findBySemester(timetable.getSemester());
            if (existingTimetable != null) {
                existingTimetable.setLink(timetable.getLink());
                timetableService.updateTimetable(existingTimetable);
                return ResponseEntity.ok(new ApiResponse_Timetable(true, "Timetable Updated!"));
            } else {
                timetableService.addTimetable(timetable);
                return ResponseEntity.ok(new ApiResponse_Timetable(true, "Timetable Added!"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse_Timetable(false, "Internal Server Error"));
        }
    }

//    @DeleteMapping("/deleteTimetable/{semester}")
//    public ResponseEntity<?> deleteTimetable(@PathVariable int semester) {
//        boolean deleted = timetableService.deleteTimetable(semester);
//        if (deleted) {
//            return ResponseEntity.ok(new ApiResponse_Timetable(true, "Timetable Deleted!"));
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ApiResponse_Timetable(false, "No Timetable Exists for Semester " + semester));
//        }
//    }
}
