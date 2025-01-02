package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.NotificationRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.NotificationResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Notification;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Enums.NotificationTypeEnum;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.NotificationMapper;
import backendgrabstudent.backend_GrabStudent.Repository.NotificationRepository;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final StudentRepository studentRepository;
    private final PostRepository postRepository;
    private final RideRepository rideRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper, StudentRepository studentRepository, PostRepository postRepository, RideRepository rideRepository) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.studentRepository = studentRepository;
        this.postRepository = postRepository;
        this.rideRepository = rideRepository;
    }

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO) {
        if (!NotificationTypeEnum.isValidType(requestDTO.getType())) {
            throw new CustomException(ErrorNumber.INVALID_TYPE);
        }

        Student sender = studentRepository.findById(requestDTO.getSenderId())
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        Student recipient = studentRepository.findById(requestDTO.getRecipientId())
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));

        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setSender(sender);
        notification.setIsRead(false);
        notification.setCreateAt(LocalDateTime.now());
        notification.setType(requestDTO.getType());

        if(requestDTO.getPostId() != null) {
            if (!postRepository.existsById(requestDTO.getPostId())) {
                throw new CustomException(ErrorNumber.POST_NOT_EXISTED);
            }
            notification.setPostId(requestDTO.getPostId());
        }
        if(requestDTO.getRideId() != null) {
            if (!rideRepository.existsById(requestDTO.getRideId())) {
                throw new CustomException(ErrorNumber.RIDE_NOT_EXISTED);
            }
            notification.setRideId(requestDTO.getRideId());
        }
        if (Objects.equals(requestDTO.getType().toUpperCase(), NotificationTypeEnum.SEND_REQUEST.toString())) {
            notification.setContent(String.format("%s đã gửi yêu cầu về bài đăng của bạn", sender.getName()));
        } else if (Objects.equals(requestDTO.getType().toUpperCase(), NotificationTypeEnum.ACCEPT_REQUEST.toString())) {
            notification.setContent(String.format("%s đã chấp nhận yêu cầu của bạn", sender.getName()));
        } else if (Objects.equals(requestDTO.getType().toUpperCase(), NotificationTypeEnum.REJECT_REQUEST.toString())) {
            notification.setContent(String.format("%s đã từ chối yêu cầu của bạn", sender.getName()));
        } else if (Objects.equals(requestDTO.getType().toUpperCase(), NotificationTypeEnum.CANCEL_REQUEST.toString())) {
            notification.setContent(String.format("%s đã hủy yêu cầu của họ trong bài đăng của bạn", sender.getName()));
        } else if (Objects.equals(requestDTO.getType().toUpperCase(), NotificationTypeEnum.REVIEW.toString())) {
            notification.setContent(String.format("%s đã gửi đánh giá về bạn trong một chuyến đi", sender.getName()));
        } else if (Objects.equals(requestDTO.getType().toUpperCase(), NotificationTypeEnum.CANCEL_RIDE.toString())) {
            notification.setContent(String.format("%s đã hủy chuyến đi của họ và bạn", sender.getName()));
        }

        Notification savedNotification = notificationRepository.save(notification);
        return notificationMapper.toResponseDTO(savedNotification);
    }

    @Override
    public NotificationResponseDTO getNotificationById(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.NOTIFICATION_NOT_EXISTED));
        return notificationMapper.toResponseDTO(notification);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByStudentId(Integer studentId) {
        List<Notification> notifications = notificationRepository.findAllByRecipientIdOrderByCreateAtDesc(studentId);
        return notifications.stream()
                .map(notificationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.NOTIFICATION_NOT_EXISTED));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Long countUnreadNotifications(Integer recipientId) {
        return notificationRepository.countUnreadNotificationsByRecipientId(recipientId);
    }
}
