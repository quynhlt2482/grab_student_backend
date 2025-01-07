package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.NotificationRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {
    NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO);

    NotificationResponseDTO getNotificationById(Integer id);

    List<NotificationResponseDTO> getNotificationsByStudentId(Integer studentId);

    void markAsRead(Integer id);

    void deleteNotification(Integer id);

    Long countUnreadNotifications(Integer recipientId);
}
