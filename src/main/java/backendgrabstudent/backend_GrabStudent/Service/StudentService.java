package backendgrabstudent.backend_GrabStudent.Service;


import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.StudentPasswordUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.LoginResponse;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentManagerReponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.VerifyOtpResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentResponseDTO> getAllStudent();

    Optional<StudentResponseDTO> getStudentById(int id);

    StudentResponseDTO saveStudent(StudentResponseDTO student);

    StudentResponseDTO updateStudent(StudentResponseDTO student);

    void deleteStudent(int id);

    void updatePassword(int id, StudentPasswordUpdateDTO request);

    Optional<StudentResponseDTO> getStudentLoginInfor();

    StudentManagerReponseDTO updateUserinManager(StudentManagerReponseDTO studentManagerReponseDTO);

    List<StudentManagerReponseDTO> getAllStudentManagerReponse();

    LoginResponse verifyOtp(String email, String otp);

    void change2fa(int id, boolean isEnabled);

    Float getStudentRating(int id);
}
