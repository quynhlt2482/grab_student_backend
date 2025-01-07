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

import java.time.LocalDate;
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
    public ResponseObject<List<PostResponseDTO>> getPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return ResponseObject.<List<PostResponseDTO>>builder()
                .data(posts)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseObject<PostResponseDTO> getById(@PathVariable Integer id) {
        PostResponseDTO post = postService.getById(id);
        return ResponseObject.<PostResponseDTO>builder()
                .data(post)
                .build();
    }

    @GetMapping("/rider")
    public ResponseObject<List<PostResponseDTO>> getAllPostsRides(@RequestParam boolean status, @RequestParam int userId) {
        List<PostResponseDTO> posts = postService.getAllPostsRide(status, userId);
        return ResponseObject.<List<PostResponseDTO>>builder()
                .data(posts)
                .build();
    }

    @GetMapping("/passenger")
    public ResponseObject<List<PostResponseDTO>> getAllPostsCustomer(@RequestParam boolean status, @RequestParam int userId) {
        List<PostResponseDTO> posts = postService.getAllPostsCustomer(status, userId);
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

    @GetMapping("/getByCurrentUser")
    public ResponseObject<List<PostResponseDTO>> getByCurrentUser(
            @RequestParam("postType") String postType,
            @RequestParam(value = "status") Boolean status,
            @RequestParam("startDateFrom") LocalDate startDateFrom,
            @RequestParam("startDateTo") LocalDate startDateTo
    ) {
        List<PostResponseDTO> posts = postService.getByCurrentUser(postType, status, startDateFrom, startDateTo);
        return ResponseObject.<List<PostResponseDTO>>builder()
                .data(posts)
                .build();
    }

}
