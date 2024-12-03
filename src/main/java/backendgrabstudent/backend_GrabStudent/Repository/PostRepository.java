package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "SELECT new backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO(p.id, s.id, p.pickUpLocation, p.dropOffLocation,p.status, p.type, " +
            "p.pickUpLat, p.pickUpLon, p.dropOffLat, p.dropOffLon, p.startDate, p.startTimeString) " +
            "FROM Post p JOIN p.student s WHERE p.type = :type")
    List<PostResponseDTO> findByType(String type);
}
