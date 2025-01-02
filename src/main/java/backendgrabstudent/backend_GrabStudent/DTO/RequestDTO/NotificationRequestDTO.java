package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequestDTO {
    @NotBlank(message = "type must not be blank")
    private String type;
    @NotNull(message = "senderId must not be null")
    private Integer senderId;
    @NotNull(message = "recipientId must not be null")
    private Integer recipientId;

    private Integer postId;
    private Integer rideId;
}
