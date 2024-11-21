package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.PostDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.CookieStore;
import java.util.List;

@Service
public class PostServiceImple implements PostService{
    private PostRepository postRepository;
    private HttpServletRequest request;

    @Autowired
    public PostServiceImple(PostRepository postRepository, HttpServletRequest request) {
        this.postRepository = postRepository;
        this.request = request;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<PostDTO> getAllPostsRide() {
        return postRepository.findByType("ride");
    }

    @Override
    public List<PostDTO> getAllPostsCustomer() {
        return postRepository.findByType("customer");
    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    private Integer getStudentIdFromCookies() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("studentId".equals(cookie.getName())) {
                    return Integer.parseInt(cookie.getValue());
                }
            }
        }
        return null;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        // Lấy studentId từ payload
        Integer studentId = postDTO.getStudentId();
        if (studentId == null) {
            throw new RuntimeException("Student ID is missing in the request payload");
        }

        // Gán studentId vào postDTO
        postDTO.setStudentId(studentId);

        // Chuyển đổi PostDTO thành Post Entity
        Post post = new Post();
        post.setPickUpLocation(postDTO.getPickUpLocation());
        post.setDropOffLocation(postDTO.getDropOffLocation());
        post.setStatus(postDTO.getStatus());
        post.setType(postDTO.getType());
        post.setPickUpLat(postDTO.getPickUpLat());
        post.setPickUpLon(postDTO.getPickUpLon());
        post.setDropOffLat(postDTO.getDropOffLat());
        post.setDropOffLon(postDTO.getDropOffLon());
        post.setStartDate(postDTO.getStartDate());
        post.setStartTimeString(postDTO.getStartTimeString());

        // Lưu bài đăng
        Post savedPost = postRepository.save(post);

        // Trả về PostDTO sau khi lưu
        return new PostDTO(savedPost.getId(), studentId, savedPost.getPickUpLocation(),
                savedPost.getDropOffLocation(), savedPost.getStatus(), savedPost.getType(),
                savedPost.getPickUpLat(), savedPost.getPickUpLon(), savedPost.getDropOffLat(),
                savedPost.getDropOffLon(), savedPost.getStartDate(), savedPost.getStartTimeString());
    }

}
