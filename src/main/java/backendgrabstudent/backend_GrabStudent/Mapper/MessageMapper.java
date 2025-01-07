package backendgrabstudent.backend_GrabStudent.Mapper;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.MessageRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.MessageResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Conversation;
import backendgrabstudent.backend_GrabStudent.Entity.Message;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface MessageMapper {

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "recipient.id", target = "recipientId")
    @Mapping(source = "conversation.id", target = "conversationId")
    MessageResponseDTO toResponseDTO(Message message);
}
