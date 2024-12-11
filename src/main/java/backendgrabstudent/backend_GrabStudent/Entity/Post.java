package backendgrabstudent.backend_GrabStudent.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String pickUpLocation;
    String dropOffLocation;
    Boolean status;
    BigDecimal pickUpLat;
    BigDecimal pickUpLon;
    BigDecimal dropOffLat;
    BigDecimal dropOffLon;
    String startDate;
    String startTimeString;
    String content;

    @ManyToOne
    @JoinColumn(name = "post_type")
    PostType postType;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @OneToMany(mappedBy = "post")
    Set<RideRequest> rideRequests;
}
