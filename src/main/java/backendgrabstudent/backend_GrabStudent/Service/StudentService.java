package backendgrabstudent.backend_GrabStudent.Service;



import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.VerifyOtpResponse;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public List<StudentResponseDTO> getAllStudent();
    public Optional<StudentResponseDTO> getStudentById(int id);
    public StudentResponseDTO saveStudent(StudentResponseDTO student);
    public StudentResponseDTO updateStudent(StudentResponseDTO student);
    public void deleteStudent(int id);
    public void updatePassword(int id, String newPassword);
    public boolean existsById(int id);
    public Optional<StudentResponseDTO> getStudentLoginInfor();
//    public String registerStudent(String email);
    public VerifyOtpResponse verifyOtp(String email, String otp);


}
