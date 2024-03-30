package me.csproconnect.backend.model.adminmodel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.schema.TypedJsonSchemaObject;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    @Id
    private boolean success;
    private String message;
    private AdminCredentials adminc;
    private AdminDetails admind;
    private Object user;
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

    public ApiResponse(boolean success, String message, AdminCredentials adminc) {
        this.success = success;
        this.message = message;
        this.adminc = adminc;
    }
    public ApiResponse(boolean success, String message, Object user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }
}
