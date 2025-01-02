package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentManagerReponseDTO {
    private Integer id;
    private String email;
    private String name;
    private Boolean gender;
    private LocalDate birthday;
    private String studentClass;
    private String phonenumber;
    private Integer ridePoint;
    private Boolean isBanned;
}
