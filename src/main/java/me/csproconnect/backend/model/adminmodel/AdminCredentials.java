package me.csproconnect.backend.model.adminmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCredentials {
    @Id
    private String id;
    private String loginid;
    private String password;
}

