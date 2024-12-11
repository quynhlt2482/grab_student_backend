package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.CreatePostRequest;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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
    public ResponseObject<List<Post>> getPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseObject.<List<Post>>builder()
                .data(posts)
                .build();
    }

    @GetMapping("/rider")
    public ResponseObject<List<PostResponseDTO>> getAllPostsRides(@RequestParam int userId) {
        List<PostResponseDTO> posts = postService.getAllPostsRide(userId);
        return ResponseObject.<List<PostResponseDTO>>builder()
                .data(posts)
                .build();
    }

    @GetMapping("/passenger")
    public ResponseObject<List<PostResponseDTO>> getAllPostsCustomer(@RequestParam int userId) {
        List<PostResponseDTO> posts = postService.getAllPostsCustomer(userId);
        return ResponseObject.<List<PostResponseDTO>>builder()
                .data(posts)
                .build();
    }

    @PostMapping("/create")
    public ResponseObject<PostResponseDTO> createPost(@Valid @RequestBody CreatePostRequest request) {
        PostResponseDTO createdPost = postService.createPost(request);
        return ResponseObject.<PostResponseDTO>builder()
                .data(createdPost)
                .build();
    }

    @PutMapping("/update/{id}")
    public ResponseObject<String> updatePost(@PathVariable Integer id, @RequestBody PostUpdateDTO postUpdateDTO) {
        postService.updatePost(id, postUpdateDTO);
        return ResponseObject.<String>builder()
                .data("Post updated successfully")
                .build();
    }

    @PutMapping("/updateAccept/{id}")
    public ResponseObject<String> updatePostAccept(@PathVariable Integer id) {
        postService.updateStatusPostbyAccept(id);
        return ResponseObject.<String>builder()
                .data("Post accept successfully.")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<String> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return ResponseObject.<String>builder()
                .data("Post with ID " + id + " deleted successfully.")
                .build();
    }


//    @GetMapping("/postByIdLogin/dateRange")
//    public ResponseEntity<List<PostResponseDTO>> getPostsByIdLoginAndDateRange(@RequestParam String startDateFrom, @RequestParam String startDateTo) {
//        List<PostResponseDTO> posts = postService.getPostsByIdLoginAndDateRange(startDateFrom,startDateTo);
//        return ResponseEntity.ok(posts);
//    }

//    @GetMapping("/postByIdLogin")
//    public ResponseEntity<List<PostResponseDTO>> getPostsByIdLogin() {
//        List<PostResponseDTO> posts = postService.getPostsByIdLogin();
//        return ResponseEntity.ok(posts);
//    }
}
