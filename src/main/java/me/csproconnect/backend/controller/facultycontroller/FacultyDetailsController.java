package me.csproconnect.backend.controller.facultycontroller;

import me.csproconnect.backend.model.facultymodel.ApiResponse;
import me.csproconnect.backend.model.facultymodel.FacultyDetails;
import me.csproconnect.backend.service.facultyservice.FacultyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty/details")
public class FacultyDetailsController {

    @Autowired
    private FacultyDetailsService facultyService;

    @PostMapping("/getDetails")
    public ResponseEntity<?> getDetails(@RequestBody FacultyDetails request) {
        try {
            FacultyDetails user = facultyService.findByEmployeeId(request.getEmployeeId());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "No Faculty Found"));
            }
            return ResponseEntity.ok(new ApiResponse(true, "Faculty Details Found!", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Internal Server Error"));
        }
    }

    @PostMapping("/addDetails")
    public ResponseEntity<?> addDetails(@RequestBody FacultyDetails faculty) {
        try {
            FacultyDetails newFaculty = facultyService.addFaculty(faculty);
            return ResponseEntity.ok(new ApiResponse(true, "Faculty Details Added!", newFaculty));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/updateDetails/{id}")
    public ResponseEntity<?> updateDetails(@PathVariable("id") String id, @RequestBody FacultyDetails faculty) {
        try {
            FacultyDetails updatedFaculty = facultyService.updateFaculty(id, faculty);
            return ResponseEntity.ok(new ApiResponse(true, "Updated Successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/deleteDetails/{id}")
    public ResponseEntity<?> deleteDetails(@PathVariable("id") String id) {
        try {
            facultyService.deleteFaculty(id);
            return ResponseEntity.ok(new ApiResponse(true, "Deleted Successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        try {
            long count = facultyService.countFaculties();
            return ResponseEntity.ok(new ApiResponse(true, "Count Successful!", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Internal Server Error"));
        }
    }
}
