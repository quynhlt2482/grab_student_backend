package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MessageResponseDTO {
    private Integer id;
    private Integer senderId;
    private Integer recipientId;
    private Integer conversationId;
    private String content;
    private String status;
    private LocalDateTime sendAt;
}
