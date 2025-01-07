package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentCreateRequestDTO {
    private String name;
    private String email;
    private String phonenumber;
    private String password;


}
