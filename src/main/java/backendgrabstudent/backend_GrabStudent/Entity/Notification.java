package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String content;
    private Boolean isRead;
    private LocalDateTime createAt;

    private Integer postId;

    private Integer rideId;

    @ManyToOne
    private Student sender;

    @ManyToOne
    private Student recipient;
}
