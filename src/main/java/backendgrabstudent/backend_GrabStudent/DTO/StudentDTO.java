package backendgrabstudent.backend_GrabStudent.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer id;
    private String name;
    private String email;
    private String phonenumber;
    private Integer ridePoint;
    private Boolean isBanned;
    private Boolean is2faEnabled;
    private String avatarUrl;
    private Boolean verifyStudent;
}
