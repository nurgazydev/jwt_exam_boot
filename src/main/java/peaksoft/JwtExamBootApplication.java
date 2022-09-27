package peaksoft;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import peaksoft.enums.Role;
import peaksoft.models.User;
import peaksoft.repositories.UserRepository;

@SpringBootApplication
public class JwtExamBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtExamBootApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            User user = new User();
//            user.setFirstName("Nurgazy");
//            user.setLastName("Nurmamatov");
//            user.setEmail("admin@gmail.com");
//            user.setRole(Role.ADMIN);
//            user.setPassword(passwordEncoder.encode("admin"));
//            userRepository.save(user);
//        };
//    }


}
