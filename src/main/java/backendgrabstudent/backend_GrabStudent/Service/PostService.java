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
     List<Post> getAllPosts();

     List<PostResponseDTO> getAllPostsRide(boolean status, int userId);

     List<PostResponseDTO> getAllPostsCustomer(boolean status, int userId);

     PostResponseDTO getById(int id);

     void deletePost(int id);

     void updatePost(Integer id, PostUpdateDTO postUpdateDTO);

     PostResponseDTO createPost(CreatePostRequest request);

     List<PostResponseDTO> getByCurrentUser(String postType, Boolean status, LocalDate startDateFrom, LocalDate startDateTo);

     void updateStatusPostbyAccept(int id);

    @Scheduled(fixedDelay = 300000)
     void updatePostStatusInBatch();
}
