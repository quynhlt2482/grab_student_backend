package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private Boolean gender;
    private String phonenumber;
    private Integer ridePoint;
    private Boolean isBanned;
    private Boolean is2faEnabled;
    private String avatarUrl;
    private Boolean verifyStudent;
    private LocalDate birthday;
    private String studentClass;
}
