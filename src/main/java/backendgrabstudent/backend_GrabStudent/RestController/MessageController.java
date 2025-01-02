package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.MessageRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.MessageResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.Service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<ResponseObject<MessageResponseDTO>> sendMessage(@RequestBody @Valid MessageRequestDTO requestDTO) {
        MessageResponseDTO responseDTO = messageService.sendMessage(requestDTO);
        return ResponseEntity.ok(
                ResponseObject.<MessageResponseDTO>builder()
                        .data(responseDTO)
                        .build()
        );
    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<ResponseObject<List<MessageResponseDTO>>> getMessages(
            @PathVariable Integer conversationId) {
        List<MessageResponseDTO> messages = messageService.getMessagesByConversationId(conversationId);
        return ResponseEntity.ok(
                ResponseObject.<List<MessageResponseDTO>>builder()
                        .data(messages)
                        .build()
        );
    }
}
