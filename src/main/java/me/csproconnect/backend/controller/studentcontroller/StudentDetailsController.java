package me.csproconnect.backend.controller.studentcontroller;

import me.csproconnect.backend.model.studentmodel.ApiResponse;
import me.csproconnect.backend.model.studentmodel.StudentDetails;
import me.csproconnect.backend.service.studentservice.StudentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentDetailsController {

    @Autowired
    private StudentDetailsService studentDetailsService;

    @PostMapping("/getDetails")
    public ResponseEntity<?> getDetails(@RequestBody StudentDetails request) {
        try {
            StudentDetails studentDetails = studentDetailsService.findByEnrollmentNo(request.getEnrollmentNo());
            if (studentDetails == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "No Student Found"));
            }
            return ResponseEntity.ok(new ApiResponse(true, "Student Details Found!", studentDetails));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Internal Server Error"));
        }
    }

    @PostMapping("/addDetails")
    public ResponseEntity<?> addDetails(@RequestBody StudentDetails studentDetails) {
        try {
            StudentDetails newStudentDetails = studentDetailsService.addStudentDetails(studentDetails);
            return ResponseEntity.ok(new ApiResponse(true, "Student Details Added!", newStudentDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/updateDetails/{id}")
    public ResponseEntity<?> updateDetails(@PathVariable("id") String id, @RequestBody StudentDetails studentDetails) {
        try {
            StudentDetails updatedStudentDetails = studentDetailsService.updateStudentDetails(id, studentDetails);
            return ResponseEntity.ok(new ApiResponse(true, "Updated Successfully!", updatedStudentDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/deleteDetails/{id}")
    public ResponseEntity<?> deleteDetails(@PathVariable("id") String id) {
        studentDetailsService.deleteStudentDetails(id);
        return ResponseEntity.ok(new ApiResponse(true, "Deleted Successfully!"));
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCount() {
        try {
            long count = studentDetailsService.getCount();
            return ResponseEntity.ok(new ApiResponse(true, "Count Successful!", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Internal Server Error"));
        }
    }
}

