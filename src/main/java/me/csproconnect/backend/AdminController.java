package me.csproconnect.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminCredentialRepository adminCredentialRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {
        String loginid = request.getLoginid();
        String password = request.getPassword();
        AdminCredential user = adminCredentialRepository.findByLoginid(loginid);
        if (user == null || !password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Wrong Credentials"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Login Successful!", user.getLoginid(), user.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AdminRegistrationRequest request) {
        String loginid = request.getLoginid();
        String password = request.getPassword();
        AdminCredential existingUser = adminCredentialRepository.findByLoginid(loginid);
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Admin with this login id already exists"));
        }
        AdminCredential newUser = new AdminCredential(loginid, password);
        adminCredentialRepository.save(newUser);
        return ResponseEntity.ok(new ApiResponse(true, "Registration Successful!", newUser.getLoginid(), newUser.getId()));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody AdminUpdateRequest request) {
        AdminCredential existingUser = adminCredentialRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "No Admin exists with the provided id"));
        }
        existingUser.setLoginid(request.getLoginid());
        existingUser.setPassword(request.getPassword());
        adminCredentialRepository.save(existingUser);
        return ResponseEntity.ok(new ApiResponse(true, "Update Successful!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        AdminCredential existingUser = adminCredentialRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "No Admin exists with the provided id"));
        }
        adminCredentialRepository.delete(existingUser);
        return ResponseEntity.ok(new ApiResponse(true, "Deletion Successful!"));
    }
}
