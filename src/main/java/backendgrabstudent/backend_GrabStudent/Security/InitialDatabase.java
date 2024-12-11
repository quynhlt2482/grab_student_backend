package backendgrabstudent.backend_GrabStudent.Security;

import backendgrabstudent.backend_GrabStudent.Entity.PostType;
import backendgrabstudent.backend_GrabStudent.Entity.Student;
import backendgrabstudent.backend_GrabStudent.Enums.PostTypeEnum;
import backendgrabstudent.backend_GrabStudent.Repository.PostTypeRepository;
import backendgrabstudent.backend_GrabStudent.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDatabase {
    @Bean
    CommandLineRunner runner(PostTypeRepository postTypeRepository, StudentRepository studentRepository) {
        return args -> {
            if (!postTypeRepository.existsById(PostTypeEnum.PASSENGER.toString())) {
                PostType passenger = PostType.builder()
                        .name(PostTypeEnum.PASSENGER.toString())
                        .description("PASSENGER post type")
                        .build();
                postTypeRepository.save(passenger);
            }

            if (!postTypeRepository.existsById(PostTypeEnum.RIDER.toString())) {
                PostType passenger = PostType.builder()
                        .name(PostTypeEnum.RIDER.toString())
                        .description("RIDER post type")
                        .build();
                postTypeRepository.save(passenger);
            }

            if (!postTypeRepository.existsById(PostTypeEnum.RIDER.toString())) {
                PostType passenger = PostType.builder()
                        .name(PostTypeEnum.RIDER.toString())
                        .description("RIDER post type")
                        .build();
                postTypeRepository.save(passenger);
            }

            if(!studentRepository.existsByEmail("theboyhunter123@gmail.com")) {
                Student student = Student.builder()
                        .email("theboyhunter123@gmail.com")
                        .password("123qweasd")
                        .name("Huynh Thanh Tien")
                        .avatarUrl("https://kenh14cdn.com/203336854389633024/2022/2/20/photo-1-1645327754811906901966.jpg")
                        .gender(true)
                        .is2faEnabled(false)
                        .isBanned(false)
                        .phonenumber("0325677401")
                        .build();
                studentRepository.save(student);
            }
        };
    }
}
