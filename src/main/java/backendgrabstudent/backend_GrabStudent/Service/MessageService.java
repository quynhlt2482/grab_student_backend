package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.MessageRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.SeenMessagesRequest;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.MessageResponseDTO;

import java.util.List;

public interface MessageService {
    MessageResponseDTO sendMessage(MessageRequestDTO messageRequestDTO);

    List<MessageResponseDTO> getMessagesByConversationId(Integer conversationId);

    List<MessageResponseDTO> getMessages(Integer senderId, Integer recipientId);

    Integer getUnreadMessageCount(Integer recipientId);

    void markMessagesAsSeen(SeenMessagesRequest request);
}
