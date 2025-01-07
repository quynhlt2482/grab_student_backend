package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.Entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface  StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);

    @Query("SELECT r FROM Student r WHERE r.verifyStudent = true")
    List<Student> findAllStudent();

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.verifyStudent = false WHERE s.id = :studentId")
    void deleteStudent(@Param("studentId") int studentId);

    Boolean existsByEmail(String email);
}