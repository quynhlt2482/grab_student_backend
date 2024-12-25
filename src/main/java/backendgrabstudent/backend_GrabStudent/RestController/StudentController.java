package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.DTO.RequestDTO.StudentPasswordUpdateDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.LoginResponse;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.ResponseObject;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.StudentResponseDTO;
import backendgrabstudent.backend_GrabStudent.DTO.ResponseDTO.VerifyOtpResponse;
import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Service.StudentService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // API để đăng ký tài khoản và gửi OTP
//    @PostMapping("/register")
//    public String register(@RequestParam String email) {
//        return studentService.registerStudent(email);
//    }

    // API để xác thực OTP
    @PostMapping("/verifyOtp")
    public ResponseObject<LoginResponse> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        var result = studentService.verifyOtp(email, otp);

        return ResponseObject.<LoginResponse>builder()
                .data(result)
                .build();
    }

    @PutMapping("updatePassword/{id}")
    public ResponseObject<?> updatePassword(
            @PathVariable int id,
            @Valid @RequestBody StudentPasswordUpdateDTO request
    ) {
        studentService.updatePassword(id, request);

        return ResponseObject.<Void>builder()
                .data(null)
                .build();
    }

    @PutMapping("/update2fa")
    public ResponseObject<?> update2fa(@RequestParam Integer id, @RequestParam Boolean isEnabled) {
        studentService.change2fa(id, isEnabled);
        return ResponseObject.<Void>builder()
                .data(null)
                .build();
    }

    @GetMapping("/All")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/information")
    public Optional<StudentResponseDTO> getStudentLoginInfor() {
        return studentService.getStudentLoginInfor();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody StudentResponseDTO studentResponseDTO) {
        StudentResponseDTO createdStudent = studentService.saveStudent(studentResponseDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Student created with ID: " + createdStudent.getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id, @RequestBody StudentResponseDTO studentResponseDTO) {
        studentResponseDTO.setId(id);
        studentService.updateStudent(studentResponseDTO);
        return ResponseEntity.ok("Student updated successfully");
    }


}
