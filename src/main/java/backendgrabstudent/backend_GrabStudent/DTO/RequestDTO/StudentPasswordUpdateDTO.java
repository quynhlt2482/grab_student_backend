package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentPasswordUpdateDTO {
    @NotBlank(message = "password must not be blank")
    String password;
    @NotBlank(message = "newPassword must not be blank")
    String newPassword;
}
