package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentManagerReponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String phonenumber;
    private Integer ridePoint;
    private Boolean isBanned;
}
