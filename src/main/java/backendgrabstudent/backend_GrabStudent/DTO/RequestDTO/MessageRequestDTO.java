package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDTO {
    @NotNull(message = "senderId must not be null")
    private Integer senderId;
    @NotNull(message = "recipientId must not be null")
    private Integer recipientId;
    @NotNull(message = "conversationId must not be null")
    private Integer conversationId;
    @NotBlank(message = "content must not be blank")
    private String content;
}
