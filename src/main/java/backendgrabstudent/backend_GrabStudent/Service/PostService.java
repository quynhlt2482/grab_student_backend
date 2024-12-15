package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.CreatePostRequest;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostFilterRequest;
import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

public interface PostService {
    public List<Post> getAllPosts();

    public List<PostResponseDTO> getAllPostsRide(int userId);

    public List<PostResponseDTO> getAllPostsCustomer(int userId);

    public void deletePost(int id);

    public void updatePost(Integer id, PostUpdateDTO postUpdateDTO);

    public PostResponseDTO createPost(CreatePostRequest request);

    public List<PostResponseDTO> getPostsByIdLogin(String postType, Boolean status, LocalDate startDateFrom, LocalDate startDateTo);

    //    public List<PostResponseDTO> getPostsByIdLoginAndDateRange(String startDateFrom, String startDateTo );
    public PostResponseDTO getPostById(int id);

    public void updateStatusPostbyAccept(int id);

    @Scheduled(fixedDelay = 300000)
    public void updatePostStatusInBatch();
}
