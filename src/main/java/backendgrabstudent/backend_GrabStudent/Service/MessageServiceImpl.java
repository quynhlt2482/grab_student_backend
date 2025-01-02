package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.MessageRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.MessageResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Conversation;
import backendgrabstudent.backend_GrabStudent.Entity.Message;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Enums.MessageStatus;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.MessageMapper;
import backendgrabstudent.backend_GrabStudent.Repository.ConversationMemberRepos;
import backendgrabstudent.backend_GrabStudent.Repository.ConversationRepos;
import backendgrabstudent.backend_GrabStudent.Repository.MessageRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepos conversationRepository;
    private final StudentRepository studentRepository;
    private final MessageMapper messageMapper;
    private final ConversationMemberRepos conversationMemberRepos;

    @Override
    public MessageResponseDTO sendMessage(MessageRequestDTO messageRequestDTO) {
        // Fetch entities
        Student sender = studentRepository.findById(messageRequestDTO.getSenderId())
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        Student recipient = studentRepository.findById(messageRequestDTO.getRecipientId())
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        Conversation conversation = conversationRepository.findById(messageRequestDTO.getConversationId())
                .orElseThrow(() -> new CustomException(ErrorNumber.CONVERSATION_NOT_EXISTED));

        // Map DTO to Entity and save
        Message message = Message.builder()
                .sender(sender)
                .recipient(recipient)
                .conversation(conversation)
                .content(messageRequestDTO.getContent())
                .status(MessageStatus.SENT.toString())
                .sendAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Message savedMessage = messageRepository.save(message);

        return messageMapper.toResponseDTO(savedMessage);
    }

    @Override
    public List<MessageResponseDTO> getMessagesByConversationId(Integer conversationId) {
        List<Message> messages = messageRepository.findByConversationIdOrderBySendAtAsc(conversationId);
        return messages.stream()
                .map(messageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}

