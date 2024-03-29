package me.csproconnect.backend.controller.admincontroller;

import me.csproconnect.backend.model.adminmodel.AdminDetails;
import me.csproconnect.backend.model.adminmodel.ApiResponse;
import me.csproconnect.backend.service.adminservice.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/details")
public class AdminDetailsController {

    @Autowired
    private AdminDetailsService adminService;

    @PostMapping("/getDetails")
    public ResponseEntity<?> getDetails(@RequestBody AdminDetails admin) {
        AdminDetails foundAdmin = adminService.findByEmployeeId(admin.getEmployeeId());
        if (foundAdmin == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "No Admin Found"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Admin Details Found!", foundAdmin));
    }

    @PostMapping("/addDetails")
    public ResponseEntity<?> addDetails(@RequestBody AdminDetails admin) {
        try {
            AdminDetails newAdmin = adminService.addAdmin(admin);
            return ResponseEntity.ok(new ApiResponse(true, "Admin Details Added!", newAdmin));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/updateDetails/{id}")
    public ResponseEntity<?> updateDetails(@PathVariable("id") String id, @RequestBody AdminDetails admin) {
        try {
            AdminDetails updatedAdmin = adminService.updateAdmin(id, admin);
            return ResponseEntity.ok(new ApiResponse(true, "Updated Successfully!", updatedAdmin));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }

    @DeleteMapping("/deleteDetails/{id}")
    public ResponseEntity<?> deleteDetails(@PathVariable("id") String id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok(new ApiResponse(true, "Deleted Successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, e.getMessage()));
        }
    }
}
