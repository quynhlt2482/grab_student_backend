package backendgrabstudent.backend_GrabStudent.RestController;


import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ConversationResponse;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.Service.ConversationService;


import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversation")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping("/student")
    public ResponseObject<List<ConversationResponse>> getConversationsByStudent(@RequestParam Integer studentId) {
        List<ConversationResponse> responses = conversationService.getConversationsByStudent(studentId);

        return ResponseObject.<List<ConversationResponse>>builder()
                .data(responses)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseObject<ConversationResponse> getConversationsByStudent(@RequestParam Integer studentId, @PathVariable Integer id) {
        ConversationResponse response = conversationService.getById(id, studentId);

        return ResponseObject.<ConversationResponse>builder()
                .data(response)
                .build();
    }
}
