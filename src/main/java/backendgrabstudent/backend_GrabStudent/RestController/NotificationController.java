package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.NotificationRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.NotificationResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.Service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/create")
    public ResponseObject<NotificationResponseDTO> createNotification(@Valid @RequestBody NotificationRequestDTO requestDTO) {
        var result = notificationService.createNotification(requestDTO);
        return ResponseObject.<NotificationResponseDTO>builder()
                .data(result)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseObject<NotificationResponseDTO> getNotificationById(@PathVariable Integer id) {
        var result = notificationService.getNotificationById(id);
        return ResponseObject.<NotificationResponseDTO>builder()
                .data(result)
                .build();
    }

    @GetMapping("/recipient/{studentId}")
    public ResponseObject<List<NotificationResponseDTO>> getNotificationsByStudentId(@PathVariable Integer studentId) {
        var result = notificationService.getNotificationsByStudentId(studentId);
        return ResponseObject.<List<NotificationResponseDTO>>builder()
                .data(result)
                .build();
    }

    @PutMapping("/mark-as-read/{id}")
    public ResponseObject<Void> markAsRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
        return ResponseObject.<Void>builder()
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<Void> deleteNotification(@PathVariable Integer id) {
        notificationService.deleteNotification(id);
        return ResponseObject.<Void>builder()
                .build();
    }

    @GetMapping("/count-unread")
    public ResponseObject<Long> countUnreadNotifications(@RequestParam Integer recipientId) {
        Long count = notificationService.countUnreadNotifications(recipientId);
        return ResponseObject.<Long>builder()
                .data(count)
                .build();
    }
}
