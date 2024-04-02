package me.csproconnect.backend.controller.othercontroller;

import me.csproconnect.backend.model.othermodel.ApiResponse_Mark;
import me.csproconnect.backend.model.othermodel.Mark;
import me.csproconnect.backend.service.otherservice.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

@RestController
@RequestMapping("/marks")
public class MarkController {

    @Autowired
    private MarkService marksService;

    @PostMapping("/getMarks")
    public ResponseEntity<?> getMarks(@RequestBody Mark request) {
        try {
            Mark marks = marksService.findByEnrollmentNo(request.getEnrollmentNo());
            if (marks == null) {
                return ResponseEntity.ok(new ApiResponse_Mark(false, "Marks Not Available"));
            }
            return ResponseEntity.ok(new ApiResponse_Mark(true, "All Marks Loaded!", marks));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse_Mark(false, "Internal Server Error"));
        }
    }

//    @PostMapping("/addMarks")
//    public ResponseEntity<?> addMarks(@RequestBody Mark marks) {
//        try {
//            Mark savedMarks = marksService.findByEnrollmentNo(marks.getEnrollmentNo());
//            if(savedMarks == null){
//                Mark marks =
//            }
//            return ResponseEntity.ok(new ApiResponse_Mark(true, "Marks Added/Updated!", savedMarks));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new ApiResponse_Mark(false, "Internal Server Error"));
//        }
//    }

    @PostMapping("/addMarks")
    public ResponseEntity<?> addMarks(@RequestBody Mark request) {
        try {
            Long enrollmentNo = request.getEnrollmentNo();
            Map<String, Integer> internal = request.getInternal();

            Mark existingMark = marksService.findByEnrollmentNo(enrollmentNo);
            if (existingMark != null) {
                existingMark.setInternal(internal);
                marksService.save(existingMark);
                return ResponseEntity.ok().body(new ApiResponse_Mark(true, "Marks Added"));
            } else {
                Mark newMark = new Mark();
                newMark.setEnrollmentNo(enrollmentNo);
                newMark.setInternal(internal);
                marksService.save(newMark);
                return ResponseEntity.ok().body(new ApiResponse_Mark(true, "Marks Added"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse_Mark(false, "Internal Server Error"));
        }
    }

    @DeleteMapping("/deleteMarks/{id}")
    public ResponseEntity<?> deleteMarks(@PathVariable("id") String id) {
        try {
            marksService.deleteMarks(id);
            return ResponseEntity.ok(new ApiResponse_Mark(true, "Marks Deleted!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse_Mark(false, "Internal Server Error"));
        }
    }
}
