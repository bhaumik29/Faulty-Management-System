package me.csproconnect.backend.model.studentmodel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    @Id
    private boolean success;
    private String message;
    private StudentCredentials studentc;
    private StudentDetails studentd;
    private int loginid;
    private String id;
    private long c;

    public ApiResponse(boolean success, String message, long c) {
        this.success = success;
        this.message = message;
        this.c = c;
    }

    public ApiResponse(boolean success, String message, StudentDetails studentd) {
        this.success = success;
        this.message = message;
        this.studentd = studentd;
    }

    public ApiResponse(boolean success, String message, int loginid, String id) {
        this.success = success;
        this.message = message;
        this.loginid = loginid;
        this.id = id;
    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
