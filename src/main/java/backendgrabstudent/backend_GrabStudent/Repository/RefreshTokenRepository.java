package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.Entity.RefreshToken;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findByStudent(Student student);
}
