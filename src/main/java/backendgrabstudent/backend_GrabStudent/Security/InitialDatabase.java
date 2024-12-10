package backendgrabstudent.backend_GrabStudent.Security;

import backendgrabstudent.backend_GrabStudent.Entity.PostType;
import backendgrabstudent.backend_GrabStudent.Enums.PostTypeEnum;
import backendgrabstudent.backend_GrabStudent.Repository.PostTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDatabase {
    @Bean
    CommandLineRunner runner(PostTypeRepository postTypeRepository) {
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
        };
    }
}
