package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.Entity.RideReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RideReviewRepository extends JpaRepository<RideReview, Integer> {
}
