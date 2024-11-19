package backendgrabstudent.backend_GrabStudent.Service;

import backendgrabstudent.backend_GrabStudent.DTO.PostDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import backendgrabstudent.backend_GrabStudent.Repository.PostRepository;

import java.util.List;

public interface PostService {
    public List<Post> getAllPosts();
    public List<PostDTO> getAllPostsRide();
    public List<PostDTO> getAllPostsCustomer();
    public void deletePost(int id);
    public PostDTO createPost(PostDTO postDTO);
}
