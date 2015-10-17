package org.sssta.oj;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.HandlerMapping;
import org.sssta.oj.models.User;
import org.sssta.oj.repositories.UserRepository;
import org.sssta.oj.security.UserRole;

import javax.servlet.Filter;
import java.security.Principal;

/**
 * Created by cauchywei on 15/10/12.
 */

@SpringBootApplication
@Controller
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }


//    @RequestMapping(value = "/{[path:[^\\.]*}")
//    public String redirect() {
//        return "forward:/";
//    }

//    @RequestMapping(value = "/register")
//    public String redirect() {
//        return "forward:/#/register";
//    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/{path:register|login|library|contest|rank|about}/**")
    public String register(WebRequest request) {
        String mvcPath = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        return "redirect:/#"+mvcPath;
    }

//    @RequestMapping("/library/**")
//    public String library() {
//        return "forward:/#/library";
//    }
//
//    @RequestMapping("/contest/**")
//    public String contest() {
//        return "forward:/#/contest";
//    }
//
//    @RequestMapping("/rank/**")
//    public String rank() {
//        return "forward:/#/rank";
//    }
//
//    @RequestMapping("/about/**")
//    public String about() {
//        return "forward:/#/about";
//    }
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "{a:2}";
    }


//    @RequestMapping("/**")
//    public String normal(WebRequest request) {
//
//        String mvcPath = (String) request.getAttribute(
//                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
//
//        return "forward:/#"+mvcPath;
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
