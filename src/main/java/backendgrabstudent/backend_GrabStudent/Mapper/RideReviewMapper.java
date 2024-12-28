package backendgrabstudent.backend_GrabStudent.Mapper;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideReviewRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideReviewResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.RideReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RideReviewMapper {
    RideReviewMapper INSTANCE = Mappers.getMapper(RideReviewMapper.class);

    @Mapping(target = "rideId", source = "ride.id")
    @Mapping(target = "reviewerId", source = "reviewer.id")
    @Mapping(target = "reviewedId", source = "reviewed.id")
    RideReviewResponseDTO toRideReviewResponseDTO(RideReview rideReview);

    @Mapping(target = "ride.id", source = "rideId")
    @Mapping(target = "reviewer.id", source = "reviewerId")
    @Mapping(target = "reviewed.id", source = "reviewedId")
    RideReview toRideReview(RideReviewRequestDTO rideReviewRequestDTO);
}
