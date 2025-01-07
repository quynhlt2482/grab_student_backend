package backendgrabstudent.backend_GrabStudent.Mapper;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.NotificationRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.NotificationResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationResponseDTO toResponseDTO(Notification notification);

    Notification toEntity(NotificationRequestDTO requestDTO);
}
