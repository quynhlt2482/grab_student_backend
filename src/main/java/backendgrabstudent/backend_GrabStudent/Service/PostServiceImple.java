package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import backendgrabstudent.backend_GrabStudent.Security.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImple implements PostService{
    private PostRepository postRepository;
    private HttpServletRequest request;
    private StudentRepository studentRepository;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    public PostServiceImple(PostRepository postRepository, HttpServletRequest request, StudentRepository studentRepository) {
        this.postRepository = postRepository;
        this.request = request;
        this.studentRepository = studentRepository;
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
        String authorizationHeader = request.getHeader("Authorization");
        Integer studentId = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                // Giải mã JWT và lấy claims từ token
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

                // Lấy student_id từ claims
                studentId = Integer.parseInt(claims.get("student_id").toString());

                if (studentId == null) {
                    throw new RuntimeException("Student ID is missing in the JWT token");
                }
            } catch (JwtException | IllegalArgumentException e) {
                // Nếu có lỗi khi giải mã JWT (token không hợp lệ, hết hạn, hoặc thiếu trường)
                throw new RuntimeException("Invalid or expired JWT token", e);
            }
        } else {
            throw new RuntimeException("Authorization header is missing or invalid");
        }
        // get Student for setStudent

//        try {
//            JwtUtil jwt = new JwtUtil();
//            String token = JwtUtil.extractTokenFromHeader(authorizationHeader);
//            studentId = jwt.getStudentIdFromToken(token);
//            if (studentId == null) {
//                    throw new RuntimeException("Student ID is missing in the JWT token");
//                }
//        }
//        catch (JwtException | IllegalArgumentException e) {
//                // Nếu có lỗi khi giải mã JWT (token không hợp lệ, hết hạn, hoặc thiếu trường)
//                throw new RuntimeException("Invalid or expired JWT token", e);
//            }
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

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

        // Lưu bài đăng
        Post savedPost = postRepository.save(post);

        // Trả về PostDTO sau khi lưu
        return new PostResponseDTO(savedPost.getId(), studentId, savedPost.getPickUpLocation(),
                savedPost.getDropOffLocation(), savedPost.getStatus(), savedPost.getType(),
                savedPost.getPickUpLat(), savedPost.getPickUpLon(), savedPost.getDropOffLat(),
                savedPost.getDropOffLon(), savedPost.getStartDate(), savedPost.getStartTimeString());
    }

    @Override
    public List<PostResponseDTO> getPostsByIdLogin(int id) {
        return List.of();
    }

    @Override
    public PostResponseDTO getPostById(int id) {
        return null;
    }

    @Override
    public void updatePost(Integer id, PostUpdateDTO postUpdateDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with ID " + id + " not found"));

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
        postRepository.updatePostStatusById(id);
    }

    @Override
    public void updatePostStatusInBatch() {
        LocalDateTime now = LocalDateTime.now();
        postRepository.updateExpiredPosts(now);
    }
}
