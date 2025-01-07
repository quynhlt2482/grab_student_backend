package backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationResponse {
    Integer id;
    StudentResponseDTO student;
    String lastMessage;
    LocalDateTime lastMessageTime;
    Boolean isRead;
}
