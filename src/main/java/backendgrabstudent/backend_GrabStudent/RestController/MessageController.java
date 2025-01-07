package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.MessageRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.SeenMessagesRequest;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.MessageResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.Service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseObject<MessageResponseDTO> sendMessage(@RequestBody @Valid MessageRequestDTO requestDTO) {
        MessageResponseDTO responseDTO = messageService.sendMessage(requestDTO);

        return ResponseObject.<MessageResponseDTO>builder()
                .data(responseDTO)
                .build();
    }

    @GetMapping("/{conversationId}")
    public ResponseObject<List<MessageResponseDTO>> getMessages(@PathVariable Integer conversationId) {
        List<MessageResponseDTO> messages = messageService.getMessagesByConversationId(conversationId);

        return ResponseObject.<List<MessageResponseDTO>>builder()
                .data(messages)
                .build();
    }

    @PutMapping("/seen")
    public ResponseObject<Void> sendMessage(@RequestBody SeenMessagesRequest request) {
        messageService.markMessagesAsSeen(request);

        return ResponseObject.<Void>builder().build();
    }

    @GetMapping("/count-un-read")
    public ResponseObject<Integer> getUnreadMessageCount(@RequestParam Integer recipientId) {
        Integer result = messageService.getUnreadMessageCount(recipientId);

        return ResponseObject.<Integer>builder()
                .data(result)
                .build();
    }
}
