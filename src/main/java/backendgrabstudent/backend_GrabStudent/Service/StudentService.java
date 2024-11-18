package backendgrabstudent.backend_GrabStudent.Service;



import backendgrabstudent.backend_GrabStudent.DTO.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public List<StudentDTO> getAllStudent();
    public Optional<StudentDTO> getStudentById(int id);
    public StudentDTO saveStudent(StudentDTO student);
    public StudentDTO updateStudent(StudentDTO student);
    public void deleteStudent(int id);

    public String registerStudent(String email); // Đăng ký tài khoản và gửi OTP
    public String verifyOtp(String email, String otp); // Xác thực OTP
}
