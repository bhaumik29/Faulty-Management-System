package me.csproconnect.backend.model.facultymodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "faculty credentials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyCredentials {
    @Id
    private String id;
    private int loginid;
    private String password;
}

