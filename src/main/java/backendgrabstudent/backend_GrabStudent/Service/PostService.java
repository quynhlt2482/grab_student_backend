package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;

import java.util.List;

public interface PostService {
    public List<Post> getAllPosts();
    public List<PostResponseDTO> getAllPostsRide();
    public List<PostResponseDTO> getAllPostsCustomer();
    public void deletePost(int id);
    public PostResponseDTO createPost(PostResponseDTO postResponseDTO);
    public List<PostResponseDTO> getPostsByIdLogin(int id);
    public PostResponseDTO getPostById(int id);
}
