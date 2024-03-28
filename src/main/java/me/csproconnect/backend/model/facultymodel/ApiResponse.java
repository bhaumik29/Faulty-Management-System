package me.csproconnect.backend.model.facultymodel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.csproconnect.backend.model.adminmodel.AdminCredentials;
import me.csproconnect.backend.model.adminmodel.AdminDetails;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    @Id
    private boolean success;
    private String message;
    private List<FacultyCredentials> facultyc;
    private List<FacultyDetails> facultyd;
    private int loginid;
    private String id;

    public ApiResponse(boolean success, String message, int loginid, String id) {
        this.success = success;
        this.message = message;
        this.loginid = loginid;
        this.id = id;
    }

    public ApiResponse(boolean success, String id) {
        this.success = success;
        this.id = id;
    }

    public ApiResponse(boolean success, int loginid, String id) {
        this.success = success;
        this.loginid = loginid;
        this.id = id;
    }

    public ApiResponse(boolean success, String message, List<FacultyCredentials> facultyc) {
        this.success = success;
        this.message = message;
        this.facultyc = facultyc;
    }
    public ApiResponse(boolean success, String message, List<FacultyDetails> facultyd) {
        this.success = success;
        this.message = message;
        this.facultyd = facultyd;
    }
}
