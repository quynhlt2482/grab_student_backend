package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ConversationResponse;

import java.util.List;

public interface ConversationService {
    List<ConversationResponse> getConversationsByStudent(Integer studentId);

    ConversationResponse getById(Integer id, Integer studentId);
}
