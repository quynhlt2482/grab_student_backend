package backendgrabstudent.backend_GrabStudent.RestController;

import backendgrabstudent.backend_GrabStudent.Service.EmailService;
import backendgrabstudent.backend_GrabStudent.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService, StudentService studentService) {
        this.emailService = emailService;
    }

    // Endpoint để gửi OTP
    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam String toEmail) {
        emailService.sendOTP(toEmail);
        return "OTP has been sent to " + toEmail;
    }

}
