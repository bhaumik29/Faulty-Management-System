package me.csproconnect.backend.controller.othercontroller;

import me.csproconnect.backend.model.othermodel.ApiResponse_Subject;
import me.csproconnect.backend.model.othermodel.Subject;
import me.csproconnect.backend.service.otherservice.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/getSubject")
    public ResponseEntity<?> getSubjectById(@RequestBody String id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        if (subject.isPresent()) {
            return ResponseEntity.ok(subject.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse_Subject(false, "Subject Not Found"));
        }
    }

    @GetMapping("/getAllSubjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/addSubject")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject) {
        Subject addedSubject = subjectService.addSubject(subject);
        String message = (addedSubject.getId() != null) ? "Subject Added!" : "Subject Already Exists!";
        return ResponseEntity.ok(new ApiResponse_Subject(true, message));
    }

    @DeleteMapping("/deleteSubject/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable String id) {
        boolean deleted = subjectService.deleteSubject(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse_Subject(true, "Subject Deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse_Subject(false, "No Subject Exists!"));
        }
    }
}
