package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyOtpResponse {
    Boolean isVerified;
}
