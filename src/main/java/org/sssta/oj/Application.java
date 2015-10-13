package org.sssta.oj;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.sssta.oj.models.User;
import org.sssta.oj.repositories.UserRepository;
import org.sssta.oj.security.UserRole;

import javax.servlet.Filter;

/**
 * Created by cauchywei on 15/10/12.
 */

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

//    @RequestMapping("/")
//    public String home() {
//        return "forward:/index.html";
//    }

    @Bean
    public InitializingBean insertDefaultUsers() {
        return new InitializingBean() {
            @Autowired
            private UserRepository userRepository;

            @Override
            public void afterPropertiesSet() {
//                addUser("admin", "admin");
//                addUser("user", "user");
            }

            private void addUser(String username, String password) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(new BCryptPasswordEncoder().encode(password));
                user.grantRole(username.equals("admin") ? UserRole.ADMIN : UserRole.USER);
                userRepository.save(user);
            }
        };
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }


}
