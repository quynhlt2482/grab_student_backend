package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RideRequestRepository extends JpaRepository<RideRequest, Integer> {
    @Query("SELECT r FROM RideRequest r WHERE r.post.id = :post_id")
    List<RideRequest> findByPostId(@Param("post_id") int post_id);
}
