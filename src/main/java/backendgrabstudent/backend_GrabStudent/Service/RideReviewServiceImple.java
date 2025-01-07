package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.RideReviewRequestDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.RideReviewResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Ride;
import backendgrabstudent.backend_GrabStudent.Entity.RideReview;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.RideReviewMapper;
import backendgrabstudent.backend_GrabStudent.Repository.RideRepository;
import backendgrabstudent.backend_GrabStudent.Repository.RideReviewRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideReviewServiceImple implements RideReviewService {
    private final RideReviewRepository rideReviewRepository;
    private final RideRepository rideRepository;
    private final StudentRepository studentRepository;
    private final RideReviewMapper rideReviewMapper;

    public RideReviewServiceImple(RideReviewRepository rideReviewRepository, RideRepository rideRepository, StudentRepository studentRepository, RideReviewMapper rideReviewMapper) {
        this.rideReviewRepository = rideReviewRepository;
        this.rideRepository = rideRepository;
        this.studentRepository = studentRepository;
        this.rideReviewMapper = rideReviewMapper;
    }

    @Override
    public RideReviewResponseDTO createRideReview(RideReviewRequestDTO requestDTO) {
        if (rideReviewRepository.existsByRideAndReviewerAndReviewed(
                requestDTO.getRideId(),
                requestDTO.getReviewerId(),
                requestDTO.getReviewedId()
        )){
            throw new CustomException(ErrorNumber.REVIEW_EXISTED);
        }

        Ride ride = rideRepository.findById(requestDTO.getRideId())
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_NOT_EXISTED));
        Student reviewer = studentRepository.findById(requestDTO.getReviewerId())
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        Student reviewed = studentRepository.findById(requestDTO.getReviewedId())
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));

        int updatedRidePoint = reviewed.getRidePoint();
        if (requestDTO.getRating() == 5.0f) {
            updatedRidePoint += 20;
        } else if (requestDTO.getRating() >= 4.0f) {
            updatedRidePoint += 10;
        } else if (requestDTO.getRating() >= 2.0f) {
            updatedRidePoint -= 10;
        } else if (requestDTO.getRating() >= 1.0f) {
            updatedRidePoint -= 20;
        }
        if (updatedRidePoint > 100) {
            updatedRidePoint = 100;
        }
        reviewed.setRidePoint(updatedRidePoint);

        RideReview rideReview = new RideReview();
        rideReview.setRating(requestDTO.getRating());
        rideReview.setComment(requestDTO.getComment());
        rideReview.setRide(ride);
        rideReview.setReviewer(reviewer);
        rideReview.setReviewed(reviewed);

        RideReview savedRideReview = rideReviewRepository.save(rideReview);

        // Cập nhật trung bình rating cho reviewed
        Double averageRating = rideReviewRepository
                .findAverageRatingByReviewedId(reviewed.getId())
                .orElse(0.0);
        reviewed.setRating(averageRating.floatValue());
        studentRepository.save(reviewed); // Lưu thay đổi

        return rideReviewMapper.toRideReviewResponseDTO(savedRideReview);
    }

    @Override
    public RideReviewResponseDTO getRideReviewById(Integer id) {
        RideReview rideReview = rideReviewRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        return rideReviewMapper.toRideReviewResponseDTO(rideReview);
    }

    @Override
    public List<RideReviewResponseDTO> getAllRideReviews() {
        return rideReviewRepository.findAll().stream()
                .map(rideReviewMapper::toRideReviewResponseDTO).toList();
    }

    @Override
    public RideReviewResponseDTO updateRideReview(Integer id, RideReviewRequestDTO requestDTO) {
        RideReview rideReview = rideReviewRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_REVIEW_NOT_EXISTED));

        rideReview.setRating(requestDTO.getRating());
        rideReview.setComment(requestDTO.getComment());
        rideReviewRepository.save(rideReview);

        return rideReviewMapper.toRideReviewResponseDTO(rideReview);
    }

    @Override
    public void deleteRideReview(Integer id) {
        RideReview rideReview = rideReviewRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.RIDE_REVIEW_NOT_EXISTED));
        rideReviewRepository.delete(rideReview);
    }
}
