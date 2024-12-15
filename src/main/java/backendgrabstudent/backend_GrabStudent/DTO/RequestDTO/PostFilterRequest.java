package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostFilterRequest {
    @NotNull(message = "studentId must not be null")
    Integer studentId;

    @NotBlank(message = "postType must not be blank")
    String postType;

    Boolean isExpiry;

    @NotBlank(message = "startDateFrom must not be blank")
    String startDateFrom;

    @NotBlank(message = "startDateTo must not be blank")
    String startDateTo;
}
