package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImple implements PostService{
    private final PostRepository postRepository;
    private final HttpServletRequest request;
    private final StudentRepository studentRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public PostServiceImple(PostRepository postRepository, HttpServletRequest request, StudentRepository studentRepository, JwtUtil jwtUtil) {
        this.postRepository = postRepository;
        this.request = request;
        this.studentRepository = studentRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<PostResponseDTO> getAllPostsRide() {
        return postRepository.findByType("ride");
    }

    @Override
    public List<PostResponseDTO> getAllPostsCustomer() {
        return postRepository.findByType("customer");
    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponseDTO createPost(PostResponseDTO postResponseDTO) {
        Integer studentId = jwtUtil.extractStudentIdFromRequest(request);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        Post post = getPost(postResponseDTO, student);
        Post savedPost = postRepository.save(post);
        return new PostResponseDTO(savedPost.getId(), studentId, savedPost.getPickUpLocation(),
                savedPost.getDropOffLocation(), savedPost.getStatus(), savedPost.getType(),
                savedPost.getPickUpLat(), savedPost.getPickUpLon(), savedPost.getDropOffLat(),
                savedPost.getDropOffLon(), savedPost.getStartDate(), savedPost.getStartTimeString());
    }

    private static Post getPost(PostResponseDTO postResponseDTO, Student student) {
        Post post = new Post();

        post.setStudent(student);
        post.setPickUpLocation(postResponseDTO.getPickUpLocation());
        post.setDropOffLocation(postResponseDTO.getDropOffLocation());
        post.setStatus(true);
        post.setType(postResponseDTO.getType());
        post.setPickUpLat(postResponseDTO.getPickUpLat());
        post.setPickUpLon(postResponseDTO.getPickUpLon());
        post.setDropOffLat(postResponseDTO.getDropOffLat());
        post.setDropOffLon(postResponseDTO.getDropOffLon());
        post.setStartDate(postResponseDTO.getStartDate());
        post.setStartTimeString(postResponseDTO.getStartTimeString());
        return post;
    }

    @Override
    public List<PostResponseDTO> getPostsByIdLogin() {
        Integer studentId = jwtUtil.extractStudentIdFromRequest(request);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        return postRepository.findByStudentIdLogin(student.getId());
    }

    @Override
    public List<PostResponseDTO> getPostsByIdLoginAndDateRange(String startDateFrom, String startDateTo) {
        Integer studentId = jwtUtil.extractStudentIdFromRequest(request);
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new CustomException(ErrorNumber.ACCOUNT_NOT_EXISTED));
        return postRepository.findByStudentIdAndStartDateRange(student.getId(), startDateFrom, startDateTo);
    }

    @Override
    public PostResponseDTO getPostById(int id) {
        return null;
    }

    @Override
    public void updatePost(Integer id, PostUpdateDTO postUpdateDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorNumber.POST_NOT_EXISTED));

        if (postUpdateDTO.getPickUpLocation() != null) {
            post.setPickUpLocation(postUpdateDTO.getPickUpLocation());
        }
        if (postUpdateDTO.getDropOffLocation() != null) {
            post.setDropOffLocation(postUpdateDTO.getDropOffLocation());
        }
        if (postUpdateDTO.getPickUpLat() != null) {
            post.setPickUpLat(postUpdateDTO.getPickUpLat());
        }
        if (postUpdateDTO.getPickUpLon() != null) {
            post.setPickUpLon(postUpdateDTO.getPickUpLon());
        }
        if (postUpdateDTO.getDropOffLat() != null) {
            post.setDropOffLat(postUpdateDTO.getDropOffLat());
        }
        if (postUpdateDTO.getDropOffLon() != null) {
            post.setDropOffLon(postUpdateDTO.getDropOffLon());
        }
        if (postUpdateDTO.getStartDate() != null) {
            post.setStartDate(postUpdateDTO.getStartDate());
        }
        if (postUpdateDTO.getStartTimeString() != null) {
            post.setStartTimeString(postUpdateDTO.getStartTimeString());
        }

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
