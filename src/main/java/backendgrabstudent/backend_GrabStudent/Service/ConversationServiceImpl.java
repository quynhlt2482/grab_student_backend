package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ConversationResponse;
import backendgrabstudent.backend_GrabStudent.Entity.Conversation;
import backendgrabstudent.backend_GrabStudent.Entity.Message;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Enums.MessageStatus;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.StudentMapper;
import backendgrabstudent.backend_GrabStudent.Repository.ConversationRepos;
import backendgrabstudent.backend_GrabStudent.Repository.MessageRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    ConversationRepos conversationRepos;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<ConversationResponse> getConversationsByStudent(Integer studentId) {
        List<Conversation> conversations = conversationRepos.findByStudentId(studentId);
        List<ConversationResponse> conversationResponses = new ArrayList<>();

        for (Conversation conversation : conversations) {
            ConversationResponse response = new ConversationResponse();
            response.setId(conversation.getId());

            // Xác định người đối thoại
            Student otherStudent = conversation.getStudent1().getId().equals(studentId) ?
                    conversation.getStudent2() :
                    conversation.getStudent1();
            response.setStudent(studentMapper.studentToStudentResponseDTO(otherStudent));

            response.setLastMessage(conversation.getMessages().getLast().getContent());
            response.setLastMessageTime(conversation.getMessages().getLast().getSendAt());

            if(Objects.equals(conversation.getMessages().getLast().getRecipient().getId(), studentId) &&
                    Objects.equals(conversation.getMessages().getLast().getStatus(), MessageStatus.SENT.toString())
            ) {
                response.setIsRead(false);
            } else {
                response.setIsRead(true);
            }

            conversationResponses.add(response);
        }

        conversationResponses.sort(Comparator.comparing(ConversationResponse::getLastMessageTime, Comparator.nullsLast(Comparator.reverseOrder())));

        return conversationResponses;
    }

    @Override
    public ConversationResponse getById(Integer id, Integer studentId) {
        Conversation conversation = conversationRepos.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.CONVERSATION_NOT_EXISTED));

        ConversationResponse response = new ConversationResponse();
        response.setId(conversation.getId());
        // Xác định người đối thoại
        Student otherStudent = conversation.getStudent1().getId().equals(studentId) ?
                conversation.getStudent2() :
                conversation.getStudent1();
        response.setStudent(studentMapper.studentToStudentResponseDTO(otherStudent));

        response.setLastMessage(conversation.getMessages().getLast().getContent());
        response.setLastMessageTime(conversation.getMessages().getLast().getSendAt());

        if(Objects.equals(conversation.getMessages().getLast().getRecipient().getId(), studentId) &&
                Objects.equals(conversation.getMessages().getLast().getStatus(), MessageStatus.SENT.toString())
        ) {
            response.setIsRead(false);
        } else {
            response.setIsRead(true);
        }

        return response;
    }
}
