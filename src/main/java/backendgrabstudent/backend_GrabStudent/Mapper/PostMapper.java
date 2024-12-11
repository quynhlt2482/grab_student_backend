package backendgrabstudent.backend_GrabStudent.Mapper;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.PostUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.PostResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Post;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostFromDTO(PostUpdateDTO postUpdateDTO, @MappingTarget Post post);

    @Mapping(source = "student", target = "student")
    @Mapping(source = "postType.name", target = "type")
    PostResponseDTO toResponseDTO(Post post);

    // Nếu cần thêm phương thức để xử lý danh sách:
    List<PostResponseDTO> toResponseDTOs(List<Post> posts);

    // Mapping từ PostResponseDTO sang Post
    @Mapping(source = "student", target = "student")
    @Mapping(source = "type", target = "postType.name")
    Post postResponseDTOToPost(PostResponseDTO postResponseDTO);

    // Mapping từ danh sách PostResponseDTO sang danh sách Post
    List<Post> toPosts(List<PostResponseDTO> postResponseDTOs);
}

