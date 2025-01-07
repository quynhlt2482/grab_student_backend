package backendgrabstudent.backend_GrabStudent.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SeenMessagesRequest {
    private List<Integer> messageIds;
}
