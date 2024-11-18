package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // API để đăng ký tài khoản và gửi OTP
    @PostMapping("/register")
    public String register(@RequestParam String email) {
        return studentService.registerStudent(email);
    }

    // API để xác thực OTP
    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return studentService.verifyOtp(email, otp);
    }
}
