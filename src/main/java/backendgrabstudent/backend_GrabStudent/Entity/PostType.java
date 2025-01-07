package backendgrabstudent.backend_GrabStudent.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostType {
    @Id
    String name;
    String description;

    @OneToMany(mappedBy = "postType")
    Set<Post> posts;
}
