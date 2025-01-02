package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.Entity.RideReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RideReviewRepository extends JpaRepository<RideReview, Integer> {
    Boolean existsByReviewerId(Integer id);

    Boolean existsByReviewedId(Integer id);

    Boolean existsByRideId(Integer id);

    @Query("SELECT AVG(rr.rating) FROM RideReview rr WHERE rr.reviewed.id = :reviewedId")
    Optional<Double> findAverageRatingByReviewedId(@Param("reviewedId") Integer reviewedId);
}
