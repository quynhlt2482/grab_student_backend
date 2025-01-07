package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponseDTO {
    private Integer id;
    private String type;
    private String content;
    private Boolean isRead;
    private LocalDateTime createAt;
    private Integer postId;
    private Integer rideId;
    private StudentResponseDTO sender;
}