package me.csproconnect.backend;

import me.csproconnect.backend.model.adminmodel.AdminDetails;
import me.csproconnect.backend.model.adminmodel.ApiResponse;
import me.csproconnect.backend.service.adminservice.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Autowired
    private AdminDetailsService adminService;

    //	to get message hello world at localhost:8080 => just to verify the working
    @GetMapping("/temp")
    public ResponseEntity<?> gettempDetails() {
        AdminDetails foundAdmin = adminService.findByEmployeeId(111);
        if (foundAdmin == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "No Admin Found"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Admin Details Found!", foundAdmin));
    }
}

