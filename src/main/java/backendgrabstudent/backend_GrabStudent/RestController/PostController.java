package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.PostDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/rides")
    public ResponseEntity<List<PostDTO>> getAllPostsRides() {
        List<PostDTO> posts = postService.getAllPostsRide();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/customer")
    public ResponseEntity<List<PostDTO>> getAllPostsCustomer() {
        List<PostDTO> posts = postService.getAllPostsCustomer();
        return ResponseEntity.ok(posts);
    }
    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        try {
            // Gọi dịch vụ để tạo bài đăng
            PostDTO createdPost = postService.createPost(postDTO);
            return ResponseEntity.ok(createdPost);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
