package backendgrabstudent.backend_GrabStudent.Repository;

import backendgrabstudent.backend_GrabStudent.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource
public interface RideRepository extends JpaRepository<Ride, Integer> {
    Boolean existsByRideRequestId(Integer rideId);

    Boolean existsByDriverId(Integer riderId);

//    @Query("SELECT r FROM Ride r WHERE r.status = :status AND r.startTime BETWEEN :from AND :to")
//    List<Ride> findAllByStatus(@Param("status") String status, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("SELECT r FROM Ride r WHERE r.driver.id = :riderId AND r.status = :status AND r.startTime BETWEEN :from AND :to")
    List<Ride> findAllByRiderId(
            @Param("riderId") Integer riderId,
            @Param("status") String status,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    @Query("SELECT r FROM Ride r WHERE r.passenger.id = :passengerId AND r.status = :status AND r.startTime BETWEEN :from AND :to")
    List<Ride> findAllByPassengerId(
            @Param("passengerId") Integer passengerId,
            @Param("status") String status,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}
