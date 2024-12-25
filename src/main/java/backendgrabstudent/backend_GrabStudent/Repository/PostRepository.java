package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.postType.name = :type and p.status = :status and p.student.id != :userId")
    List<Post> findAllByPostTypeExcludeUserId(@Param("type") String type, @Param("status") Boolean status, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.status = false WHERE p.status = true AND CONCAT(p.startDate, 'T', p.startTimeString) <= :currentDateTime")
    void updateExpiredPosts(@Param("currentDateTime") LocalDateTime currentDateTime);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.status = false WHERE p.id = :postId")
    void updatePostStatusById(@Param("postId") Integer postId);

    @Query("SELECT p FROM Post p " +
            "WHERE p.student.id = :studentId " +
            "AND p.postType.name = :postType " +
            "AND p.startDate BETWEEN :startDateFrom AND :startDateTo " +
            "AND p.status = :status ")
    List<Post> findAllByCurrentStudent(@Param("studentId") Integer studentId,
                                       @Param("status") Boolean status,
                                       @Param("postType") String postType,
                                       @Param("startDateFrom") LocalDate startDateFrom,
                                       @Param("startDateTo") LocalDate startDateTo);
}
