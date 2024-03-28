package me.csproconnect.backend.model.facultymodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facultyDetails") // Specify the MongoDB collection name
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDetails {
    @Id
    private String id;
    private int employeeId;
}
