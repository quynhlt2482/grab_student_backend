package backendgrabstudent.backend_GrabStudent.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    Student student;

    String pickUpLocation;
    String dropOffLocation;
    Boolean status;
    BigDecimal pickUpLat;
    BigDecimal pickUpLon;
    BigDecimal dropOffLat;
    BigDecimal dropOffLon;
    String startDate;
    String startTimeString;

    @ManyToOne
    @JoinColumn(name = "post_type")
    PostType postType;
}