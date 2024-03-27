package me.csproconnect.backend.model.adminmodel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    @Id
    private boolean success;
    private String message;
    private AdminCredentials adminc;
    private AdminDetails admind;
    private String loginid;
    private String id;




    public ApiResponse(boolean success, String message, String loginid, String id) {
        this.success = success;
        this.message = message;
        this.loginid = loginid;
        this.id = id;
    }

    public ApiResponse(boolean success, String id) {
        this.success = success;
        this.id = id;
    }

    public ApiResponse(boolean success, String loginid, String id) {
        this.success = success;
        this.loginid = loginid;
        this.id = id;
    }

    public ApiResponse(boolean success, String message, AdminCredentials adminc) {
        this.success = success;
        this.message = message;
        this.adminc = adminc;
    }
    public ApiResponse(boolean success, String message, AdminDetails admind) {
        this.success = success;
        this.message = message;
        this.admind = admind;
    }
}
