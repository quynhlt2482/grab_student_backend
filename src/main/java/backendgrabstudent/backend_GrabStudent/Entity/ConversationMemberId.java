package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ConversationMemberId implements Serializable {
    private Integer conversationId;
    private Integer studentId;
}
