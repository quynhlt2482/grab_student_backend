package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private String accessToken;
    private String refreshToken;

}
