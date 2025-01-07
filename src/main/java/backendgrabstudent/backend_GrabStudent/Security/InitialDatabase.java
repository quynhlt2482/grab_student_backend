package backendgrabstudent.backend_GrabStudent.Security;

import backendgrabstudent.backend_GrabStudent.Entity.PostType;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Enums.PostTypeEnum;
import backendgrabstudent.backend_GrabStudent.Repository.PostTypeRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class InitialDatabase {
    @Bean
    CommandLineRunner runner(PostTypeRepository postTypeRepository, StudentRepository studentRepository) {
        return args -> {
            if (!postTypeRepository.existsById(PostTypeEnum.PASSENGER.toString().toLowerCase())) {
                PostType passenger = PostType.builder()
                        .name(PostTypeEnum.PASSENGER.toString().toLowerCase())
                        .description("PASSENGER post type")
                        .build();
                postTypeRepository.save(passenger);
            }

            if (!postTypeRepository.existsById(PostTypeEnum.RIDER.toString().toLowerCase())) {
                PostType passenger = PostType.builder()
                        .name(PostTypeEnum.RIDER.toString().toLowerCase())
                        .description("RIDER post type")
                        .build();
                postTypeRepository.save(passenger);
            }

            if(!studentRepository.existsByEmail("theboyhunter123@gmail.com")) {
                Student student = Student.builder()
                        .email("theboyhunter123@gmail.com")
                        .password("123qweasd")
                        .name("Ngô Lâm Phong")
                        .avatarUrl("https://inkythuatso.com/uploads/thumbnails/800/2023/02/1-mau-anh-the-nam-nen-trang-inkythuatso-27-10-34-43.jpg")
                        .gender(true)
                        .is2faEnabled(false)
                        .isBanned(false)
                        .verifyStudent(true)
                        .phonenumber("0325677401")
                        .birthday(LocalDate.of(2002, 11, 17))
                        .studentClass("K47.CNTT.C")
                        .ridePoint(100)
                        .rating(0.0f)
                        .build();
                studentRepository.save(student);
            }
            if(!studentRepository.existsByEmail("thanhquynhdhsp@gmail.com")) {
                Student student = Student.builder()
                        .email("thanhquynhdhsp@gmail.com")
                        .password("123456789")
                        .name("Quynh Toi Choi Bro")
                        .gender(true)
                        .is2faEnabled(false)
                        .isBanned(false)
                        .verifyStudent(true)
                        .phonenumber("0325677401")
                        .birthday(LocalDate.of(2002, 8, 24))
                        .studentClass("K47.CNTT.C")
                        .ridePoint(100)
                        .rating(0.0f)
                        .build();
                studentRepository.save(student);
            }
            if(!studentRepository.existsByEmail("4701104211@student.edu.vn")) {
                Student student = Student.builder()
                        .email("4701104211@student.edu.vn")
                        .password("123qweasd")
                        .name("Huỳnh Thanh Tiến")
                        .gender(true)
                        .avatarUrl("https://res.cloudinary.com/dqp3ftfyl/image/upload/v1728368370/bamito/ezvvdl3mapef2iaze7le.jpg")
                        .is2faEnabled(false)
                        .isBanned(false)
                        .verifyStudent(true)
                        .phonenumber("0325677401")
                        .birthday(LocalDate.of(2002, 11, 17))
                        .studentClass("K47.CNTT.C")
                        .ridePoint(100)
                        .rating(0.0f)
                        .build();
                studentRepository.save(student);
            }

            if(!studentRepository.existsByEmail("test@student.edu.vn")) {
                Student student = Student.builder()
                        .email("test@student.edu.vn")
                        .password("123qweasd")
                        .name("Hồ Hưng Thịnh")
                        .avatarUrl("https://res.cloudinary.com/dqp3ftfyl/image/upload/v1736267305/thinh_cqpucp.jpg")
                        .gender(false)
                        .is2faEnabled(false)
                        .isBanned(false)
                        .verifyStudent(true)
                        .phonenumber("0325677400")
                        .birthday(LocalDate.of(2002, 11, 17))
                        .studentClass("K47.CNTT.TEST")
                        .ridePoint(80)
                        .rating(0.0f)
                        .build();
                studentRepository.save(student);
            }
        };
    }
}
