package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Service.PostService;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity<List<PostResponseDTO>> getAllPostsRides() {
        List<PostResponseDTO> posts = postService.getAllPostsRide();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/customer")
    public ResponseEntity<List<PostResponseDTO>> getAllPostsCustomer() {
        List<PostResponseDTO> posts = postService.getAllPostsCustomer();
        return ResponseEntity.ok(posts);
    }
    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostResponseDTO postResponseDTO) {
            PostResponseDTO createdPost = postService.createPost(postResponseDTO);
            return ResponseEntity.ok(createdPost);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePost(
            @PathVariable Integer id,
            @RequestBody PostUpdateDTO postUpdateDTO) {

        postService.updatePost(id, postUpdateDTO);
        return ResponseEntity.ok("Post updated successfully");
    }

    @PutMapping("/updateAccept/{id}")
    public ResponseEntity<String> updatePostAccept(@PathVariable Integer id) {
            postService.updateStatusPostbyAccept(id);
            return ResponseEntity.ok("Post accept successfully.");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
            postService.deletePost(id);
            return ResponseEntity.ok("Post with ID " + id + " deleted successfully.");
    }

    @GetMapping("/postByIdLogin/dateRange")
    public ResponseEntity<List<PostResponseDTO>> getPostsByIdLoginAndDateRange(@RequestParam String startDateFrom, @RequestParam String startDateTo) {
        List<PostResponseDTO> posts = postService.getPostsByIdLoginAndDateRange(startDateFrom,startDateTo);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/postByIdLogin")
    public ResponseEntity<List<PostResponseDTO>> getPostsByIdLogin() {
        List<PostResponseDTO> posts = postService.getPostsByIdLogin();
        return ResponseEntity.ok(posts);
    }
}
