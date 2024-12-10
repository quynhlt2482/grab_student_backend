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
    public ResponseEntity<List<PostResponseDTO>> getAllPostsRides(@RequestParam int userId) {
        List<PostResponseDTO> posts = postService.getAllPostsRide(userId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/customer")
    public ResponseEntity<List<PostResponseDTO>> getAllPostsCustomer(@RequestParam int userId) {
        List<PostResponseDTO> posts = postService.getAllPostsCustomer(userId);
        return ResponseEntity.ok(posts);
    }
    @PostMapping("/create")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostResponseDTO postResponseDTO) {
//        try {
//            PostResponseDTO createdPost = postService.createPost(postResponseDTO);
//            return ResponseEntity.ok(createdPost);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
        return null;
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
        try {
            postService.updateStatusPostbyAccept(id);
            return ResponseEntity.ok("Post accept successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the post.");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok("Post with ID " + id + " deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Post with ID " + id + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the student.");
        }
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
