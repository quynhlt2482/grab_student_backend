package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "SELECT new backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO(p.id, s.id, p.pickUpLocation, p.dropOffLocation,p.status, p.type, " +
            "p.pickUpLat, p.pickUpLon, p.dropOffLat, p.dropOffLon, p.startDate, p.startTimeString) " +
            "FROM Post p JOIN p.student s WHERE p.type = :type and p.student.id != :userId")
    List<PostResponseDTO> findByType(String type, int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.status = false WHERE p.status = true AND CONCAT(p.startDate, 'T', p.startTimeString) <= :currentDateTime")
    void updateExpiredPosts(@Param("currentDateTime") LocalDateTime currentDateTime);

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.status = false WHERE p.id = :postId")
    void updatePostStatusById(@Param("postId") Integer postId);

    @Query(value = "SELECT new backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO(" +
            "p.id, s.id, p.pickUpLocation, p.dropOffLocation, p.status, p.type, " +
            "p.pickUpLat, p.pickUpLon, p.dropOffLat, p.dropOffLon, p.startDate, p.startTimeString) " +
            "FROM Post p JOIN p.student s " +
            "WHERE s.id = :studentId AND p.startDate BETWEEN :startDateFrom AND :startDateTo")
    List<PostResponseDTO> findByStudentIdAndStartDateRange(
            @Param("studentId") Integer studentId,
            @Param("startDateFrom") String startDateFrom,
            @Param("startDateTo") String startDateTo);

    @Query(value = "SELECT new backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO(" +
            "p.id, s.id, p.pickUpLocation, p.dropOffLocation, p.status, p.type, " +
            "p.pickUpLat, p.pickUpLon, p.dropOffLat, p.dropOffLon, p.startDate, p.startTimeString) " +
            "FROM Post p JOIN p.student s " +
            "WHERE s.id = :studentId")
    List<PostResponseDTO> findByStudentIdLogin(@Param("studentId") Integer studentId);

}
