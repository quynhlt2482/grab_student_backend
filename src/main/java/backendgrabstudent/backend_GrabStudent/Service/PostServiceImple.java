package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.CreatePostRequest;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostFilterRequest;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Entity.PostType;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Enums.PostStatusEnum;
import backendgrabstudent.backend_GrabStudent.Enums.PostTypeEnum;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Mapper.PostMapper;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import backendgrabstudent.backend_GrabStudent.Repository.PostTypeRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImple implements PostService {
    private final PostRepository postRepository;
    private final HttpServletRequest request;
    private final StudentRepository studentRepository;
    private final JwtUtil jwtUtil;
    private final PostMapper postMapper;
    private final PostTypeRepository postTypeRepository;

    @Autowired
    public PostServiceImple(PostRepository postRepository, HttpServletRequest request, StudentRepository studentRepository, JwtUtil jwtUtil, PostMapper postMapper, PostTypeRepository postTypeRepository) {
        this.postRepository = postRepository;
        this.request = request;
        this.studentRepository = studentRepository;
        this.jwtUtil = jwtUtil;
        this.postMapper = postMapper;
        this.postTypeRepository = postTypeRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public PostResponseDTO getById(int id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.POST_NOT_EXISTED));
        return postMapper.toResponseDTO(post);
    }

    @Override
    public List<PostResponseDTO> getAllPostsRide(boolean status, int userId) {
        List<Post> posts = postRepository.findAllByPostTypeExcludeUserId(PostTypeEnum.RIDER.toString(), status, userId);
        return postMapper.toResponseDTOs(posts);
    }

    @Override
    public List<PostResponseDTO> getAllPostsCustomer(boolean status, int userId) {
        List<Post> posts = postRepository.findAllByPostTypeExcludeUserId(PostTypeEnum.PASSENGER.toString(), status, userId);
        return postMapper.toResponseDTOs(posts);
    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponseDTO createPost(CreatePostRequest postRequest) {
//        Integer studentId = jwtUtil.extractStudentIdFromRequest(request);

        Student student = studentRepository.findById(postRequest.getStudentId())
                .orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));

        PostType postType = postTypeRepository.findById(postRequest.getType())
                .orElseThrow(() -> new CustomException(ErrorNumber.INVALID_POST_TYPE));

        Post post = Post.builder()
                .student(student)
                .postType(postType)
                .dropOffLat(postRequest.getDropOffLat())
                .dropOffLon(postRequest.getDropOffLon())
                .pickUpLat(postRequest.getPickUpLat())
                .pickUpLon(postRequest.getPickUpLon())
                .pickUpLocation(postRequest.getPickUpLocation())
                .dropOffLocation(postRequest.getDropOffLocation())
                .startDate(postRequest.getStartDate())
                .startTimeString(postRequest.getStartTimeString())
                .status(postRequest.getStatus())
                .content(postRequest.getContent())
                .build();
        Post savedPost = postRepository.save(post);

        return postMapper.toResponseDTO(savedPost);
    }

    @Override
    public List<PostResponseDTO> getByCurrentUser(String postType, Boolean status, LocalDate startDateFrom, LocalDate startDateTo) {
        Integer studentId = jwtUtil.extractStudentIdFromRequest(request);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));

        List<Post> posts = postRepository.findAllByCurrentStudent(
                student.getId(),
                status,
                postType,
                startDateFrom,
                startDateTo
        );

        return postMapper.toResponseDTOs(posts);
    }

    @Override
    public void updatePost(Integer id, PostUpdateDTO postUpdateDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.POST_NOT_EXISTED));

        if(postUpdateDTO.getType() != null) {
            PostType postType = postTypeRepository.findById(postUpdateDTO.getType())
                    .orElseThrow(() -> new CustomException(ErrorNumber.INVALID_POST_TYPE));
            post.setPostType(postType);
        }
        postMapper.updatePostFromDTO(postUpdateDTO, post);
        
        postRepository.save(post);
    }

    @Override
    public void updateStatusPostbyAccept(int id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(ErrorNumber.POST_NOT_EXISTED));
        postRepository.updatePostStatusById(post.getId());
    }

    @Override
    public void updatePostStatusInBatch() {
        LocalDateTime now = LocalDateTime.now();
        postRepository.updateExpiredPosts(now);
    }
}
