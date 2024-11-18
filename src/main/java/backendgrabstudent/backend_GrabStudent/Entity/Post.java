package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String pickUpLocation;
    private String dropOffLocation;
    private LocalDateTime startTime;
    private String status;
    private String type;
    private LocalDateTime expiryTime;
}
