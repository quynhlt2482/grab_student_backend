package backendgrabstudent.backend_GrabStudent.Mapper;

import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentResponseDTO studentToStudentResponseDTO(Student student);
    Student studentResponseDTOToStudent(StudentResponseDTO studentResponseDTO);
}
